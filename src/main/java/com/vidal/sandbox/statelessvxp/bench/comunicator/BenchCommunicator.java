package com.vidal.sandbox.statelessvxp.bench.comunicator;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidal.sandbox.statelessvxp.bench.Bench;
import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.util.Collection;

public abstract class  BenchCommunicator extends Bench {

     private Process serverProcess=null;
     Thread processInputReader=null;

    public abstract Class<?> getServerClass();
    public abstract void startCommunication()throws Exception;
    public abstract Object doBench(Class<? extends PojoFactory> factory, Integer  nbPojo) throws Exception;
    public abstract void stopCommunication()throws Exception;


    @Override
    public void initObjects(int nbObj) throws Exception {
        super.initObjects(nbObj);
        //Use JSON serializer
        Object res=getToSerialise();
        if (res instanceof Collection) res = ((Collection)res).iterator().next();
        OutputStream out = new ByteArrayOutputStream();
        new ObjectMapper().writeValue(out,res);
        setToSerialise(out.toString()+"\n");
    }

    public boolean startServer() {
        Method mainMethod = null;
        try { mainMethod = getServerClass().getMethod("main", String[].class); }catch(Exception e) {e.printStackTrace(); return false;}
        try {
            String javaCommand = makeJavaCommand(System.getProperty("java.home"));
            javaCommand+= " "+getServerClass().getName();
            ///System.out.println("STARTING server : "+javaCommand);
            setServerProcess(Runtime.getRuntime().exec(javaCommand));
            processInputReader = new Thread(new ProcessRunnable());
            processInputReader.start();

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
            processInputReader.interrupt();
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


    private class ProcessRunnable implements Runnable {
        public void run() {
          try {
            BufferedReader input = new BufferedReader(new InputStreamReader(getServerProcess().getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("[FROM SERVER] "+line);
            }
            input.close();
           }
           catch(Exception e) { e.printStackTrace(); }
        }
    }
}
