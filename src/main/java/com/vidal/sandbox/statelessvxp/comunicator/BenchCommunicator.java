package com.vidal.sandbox.statelessvxp.comunicator;


import com.vidal.sandbox.statelessvxp.serialiser.Bench;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;

public abstract class  BenchCommunicator  implements  Bench {
    public abstract Class<?> getServerClass();

    private Process serverProcess=null;


    public boolean startServer() {
        Method mainMethod = null;
        try { mainMethod = getServerClass().getMethod("main", String[].class); }catch(Exception e) {e.printStackTrace(); return false;}
        try {
            String javaCommand = makeJavaCommand(System.getProperty("java.home"));
            javaCommand+= " "+getServerClass().getName();
            System.out.println("STARTING server : "+javaCommand);
            setServerProcess(Runtime.getRuntime().exec(javaCommand));
        }
        catch(Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }


    public int stopServer() {
        if (getServerProcess() ==null) return 0;
        getServerProcess().destroy();
        int exitValue = Integer.MIN_VALUE;
        try {
            while(exitValue==Integer.MIN_VALUE) {
                Thread.sleep(1000);
                exitValue=getServerProcess().exitValue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return exitValue;
    }

    public Process getServerProcess() {
        return serverProcess;
    }

    public void setServerProcess(Process serverProcess) {
        this.serverProcess = serverProcess;
    }

    //private stuff
    private String makeJavaCommand(String javaHome) {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        String[] javaArguments = runtimeMxBean.getInputArguments().toArray(new String[]{} );
        String javaCommand = "\""+javaHome+"\\bin\\java\" ";
        Boolean newArg=true;
        for(int i = 0 ; i < javaArguments.length; i++) {
            if (javaArguments[i].contains("=")) {
                String [] split = javaArguments[i].split("=");
                String  argValue= split[1];
                while ( (i+1) <javaArguments.length  &&! javaArguments[i+1].startsWith("-") ) {
                    argValue+=javaArguments[++i];
                };
                javaArguments[i]=split[0]+"="+"\""+argValue+"\"";
            }

            javaCommand+=javaArguments[i]+" ";
        }

        String classpathArgument = System.getProperty("java.class.path");
        javaCommand +="-cp \""+classpathArgument+"\"" ;
        return javaCommand;
    }


}
