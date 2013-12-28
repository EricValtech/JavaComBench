package com.vidal.sandbox.statelessvxp.generators;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class ClassToProtBuff {

    public static final List<String> IGNORE_LIST = Arrays.asList("class");
    public static final List<Class<?>> ENUM_GENERATED = new ArrayList<Class<?>>();
    public static final Map<Class<?>, String> SCALAR_TYPES= new HashMap<Class<?>, String>() {{
        put(int.class,"int64");
        put(Integer.class,"int64");
        put(Date.class,"int64");
        put(float.class,"float");
        put(Float.class,"float");
        put(double.class,"double");
        put(Double.class,"double");
        put(boolean.class,"bool");
        put(Boolean.class,"bool");
        put(String.class,"string");
    }};
    private static final String REQUIRED = "required";
    private static final String REPEATED = "repeated";

    static public String classToProtoBuffFile(Class<?> classToProtify, boolean generatePackage) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        if (generatePackage) writer.println("package " + createProtBuffPackageName(classToProtify)+";");
        writer.println("message "+classToProtify.getSimpleName() +" {");
        List<String> otherClasses = protifyTypes(writer, classToProtify, 1);
        writer.println(" }");

        for(String otherClass : otherClasses) {
            writer.println();
            writer.println(otherClass);
        }
        out.close();
        writer.close();
        return out.toString();
    }

    private static String createProtBuffPackageName(Class<?> classToProtify) {
        return classToProtify.getPackage().getName() + ".protbufgen";
    }

    private static List<String> protifyTypes(PrintWriter writer, Class<?> classToProtify, int indent)
            throws Exception {
        int rank=1;
        List<String> otherClasses = new ArrayList<String>();
        BeanInfo bi = Introspector.getBeanInfo(classToProtify);
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for (int i = 0 ; i  < pds.length; i++ ) {
            String scalarType = SCALAR_TYPES.get(pds[i].getPropertyType());
            if(IGNORE_LIST.contains(pds[i].getName())) continue;
            if (scalarType != null) { writer.println(indent(indent)+ REQUIRED + " " +scalarType+" "+pds[i].getName()+"="+rank+++";"); continue;}
            if( Enum.class.isAssignableFrom(pds[i].getPropertyType())) {
                protifyEnum(writer,pds[i].getPropertyType(),indent);
                writer.println(indent(indent)+ REQUIRED + " " +pds[i].getPropertyType().getSimpleName()+" "+pds[i].getName()+" = "+rank+++";");
                continue;
            }
            if( Collection.class.isAssignableFrom(pds[i].getPropertyType())) {
                String otherClass = protifyRepeat(writer, classToProtify, pds[i], indent, rank++);
                if (otherClass !=null && ! otherClasses.contains(otherClass))otherClasses.add(otherClass);
                continue;
            }
            if( pds[i].getPropertyType().isArray()) {
                String otherClass = protifyRepeat(writer, pds[i].getPropertyType(), pds[i], indent, rank++);
                if (otherClass !=null && ! otherClasses.contains(otherClass))otherClasses.add(otherClass);
                continue;
            }
            String simpleClassName=pds[i].getPropertyType().getSimpleName();
            writer.println(indent(indent)+ REQUIRED + " " +simpleClassName+" "+pds[i].getName()+" = "+rank+++";");
            if (! otherClasses.contains(simpleClassName))otherClasses.add(classToProtoBuffFile(pds[i].getPropertyType(), false));
        }
        return otherClasses;
    }

    private static String protifyRepeat(PrintWriter writer, Class<?> classToProtify, PropertyDescriptor pd, int indent, int rank) throws Exception {
        Class<?> listClass=null;
        if (! classToProtify.isArray()) {
            Field listField = classToProtify.getDeclaredField(pd.getName());
            ParameterizedType listType = (ParameterizedType) listField.getGenericType();
            listClass = (Class<?>) listType.getActualTypeArguments()[0];
        }
        else {
            listClass =  classToProtify.getComponentType();
        }
        if(SCALAR_TYPES.get(listClass) != null) {// Collection of SCALAR
            writer.println(indent(indent)+ REPEATED + " " +SCALAR_TYPES.get(listClass)+" "+pd.getName()+" = "+rank+++";");
            return null;
        }
        else if (Enum.class.isAssignableFrom(listClass)) {
            protifyEnum(writer,listClass,indent);
            writer.println(indent(indent) + REPEATED + " " + listClass.getSimpleName() + " " + pd.getName() + " = " + rank+++";");
            return null;
        }
        else {
            writer.println(indent(indent)+ REPEATED + " " +listClass.getSimpleName()+" "+pd.getName()+" = "+rank+++";");
            return classToProtoBuffFile(listClass, false);
        }
    }

    private static String indent(int indent) {
        if (indent < 0 ) return "";
        return new String(new char[indent]).replace("\0", "\t");
    }

    private static void protifyEnum(PrintWriter writer, Class<?> enumType, int indent) throws IllegalAccessException, InstantiationException {
        if (ENUM_GENERATED.contains(enumType)) return;

        writer.println(indent(indent)+"enum "+enumType.getSimpleName()+" {");
        Class<? extends Enum> enumClass =(Class<? extends Enum>) enumType;
        Enum[] possibleValues= enumClass.getEnumConstants();
        for(int i = 0 ; i < possibleValues.length; i++) {
            //N.B.: Enum names must be unique in a package (cf. protoc error :
            // Note that enum values use C++ scoping rules, meaning that enum values are siblings of their type, not children of it.
            // Therefore "ENUM_TYPE"  must be unique within "com.package.**", not just within "EnumName".)
            writer.println(indent(indent+1)+enumType.getSimpleName()+"_"+possibleValues[i]+" = "+possibleValues[i].ordinal()+";");
        }

        writer.println(indent(indent)+" }");
        ENUM_GENERATED.add(enumType);

    }
}