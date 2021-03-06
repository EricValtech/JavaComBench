package com.vidal.sandbox.statelessvxp.util;

import com.vidal.sandbox.statelessvxp.bench.Bench;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassFinder {


    public static Map<String,Class<?>> makeBencnhMap(String scannedPackage, Class<?> root)  {
        Map<String,Class<?>> res = new HashMap<String, Class<?>>();
        if (! Bench.class.isAssignableFrom(root)) {
            throw new RuntimeException("Scanned classes should extent Bench.class ["+root.getName()+"] doesn't");
        }
        try {
            for( Class<?> currClass : findClassesForPackage(scannedPackage,root)) {
                res.put(((Bench)(currClass.newInstance())).getShortName(),currClass);
            }
        }
        catch (Exception e) { throw new RuntimeException(e); }
        return res;
    }


    public final static List<Class<?>> findClassesForPackage( String scannedPackage, Class<?> root) throws Exception {
        List<Class<?>> scannClass = findClassesForPackage(scannedPackage);
        List<Class<? >> res = new ArrayList<Class<? >>();
        for (Class<?> currClass : scannClass ) {
            if (root.isAssignableFrom(currClass) && ! root.equals(currClass)) res.add( currClass);
        }
        return res;
    }

    private static ArrayList<Class<?>> findClassesForPackage(String pkgName) throws Exception {
      Package pkg =Package.getPackage(pkgName);
      if (pkg==null) { System.out.println("ERROR can't find package : "+pkgName); return null; }
     return getClassesForPackage(pkg);
    }
    private static ArrayList<Class<?>> getClassesForPackage(Package pkg) throws Exception {
        String pkgname = pkg.getName();
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        // Get a File object for the package
        File directory = null;
        String fullPath;
        String relPath = pkgname.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (resource == null) {
            throw new RuntimeException("No resource for " + relPath);
        }
        fullPath = resource.getFile();

        try {
            directory = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(pkgname + " (" + resource + ") does not appear to be a valid URL / URI.  Strange, since we got it from the system...", e);
        } catch (IllegalArgumentException e) {
            directory = null;
        }

        if (directory != null && directory.exists()) {
            // Get the list of the files contained in the package
            String[] files = directory.list();
            for (int i = 0; i < files.length; i++) {
                // we are only interested in .class files
                if (files[i].endsWith(".class")) {
                    // removes the .class extension
                    String className = pkgname + '.' + files[i].substring(0, files[i].length() - 6);
                    try {
                        classes.add(Class.forName(className));
                    }
                    catch (ClassNotFoundException e) {
                        throw new RuntimeException("ClassNotFoundException loading " + className);
                    }
                }
            }
        }
        else {
            try {
                String jarPath = fullPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
                JarFile jarFile = new JarFile(jarPath);
                Enumeration<JarEntry> entries = jarFile.entries();
                while(entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String entryName = entry.getName();
                    if(entryName.startsWith(relPath) && entryName.length() > (relPath.length() + "/".length())) {
                        String className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
                        try {
                            classes.add(Class.forName(className));
                        }
                        catch (ClassNotFoundException e) {
                            throw new RuntimeException("ClassNotFoundException loading " + className);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(pkgname + " (" + directory + ") does not appear to be a valid package", e);
            }
        }
        return classes;
    }



}