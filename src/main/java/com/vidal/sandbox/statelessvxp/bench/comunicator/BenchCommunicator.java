package com.vidal.sandbox.statelessvxp.bench.comunicator;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidal.sandbox.statelessvxp.bench.BenchWithServer;
import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collection;

public abstract class  BenchCommunicator extends BenchWithServer {

     private Process serverProcess=null;
     Thread processInputReader=null;

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

}
