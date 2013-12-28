package com.vidal.sandbox.statelessvxp.bench.comunicator;


import com.vidal.sandbox.statelessvxp.bench.BenchWithServer;
import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

public abstract class  BenchCommunicator extends BenchWithServer {

     private Process serverProcess=null;
     Thread processInputReader=null;

    public abstract void startCommunication()throws Exception;
    public abstract Object doBench(Class<? extends PojoFactory> factory, Integer  nbPojo) throws Exception;
    public abstract void stopCommunication()throws Exception;

}
