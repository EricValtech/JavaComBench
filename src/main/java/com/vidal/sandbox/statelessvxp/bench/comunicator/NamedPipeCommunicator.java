package com.vidal.sandbox.statelessvxp.bench.comunicator;

import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

import java.io.RandomAccessFile;

public class NamedPipeCommunicator extends BenchCommunicator {

    public RandomAccessFile PIPE;

    @Override
    public void startCommunication() throws Exception {
        if (getServerProcess() == null) { throw new RuntimeException("Server is down.. can't continue.."); }
        PIPE=  new RandomAccessFile(NamedPipeServerCommunicator.PIPE_NAME, "rw");
    }

    @Override
    public Object doBench(Class<? extends PojoFactory> factory, Integer nbPojo) throws Exception {
        if (getServerProcess() == null) { throw new RuntimeException("Server is down.. can't continue.."); }
        byte [] data = getToSerialise().toString().getBytes();
        PIPE.write(data);
        return PIPE.readInt();
    }

    @Override
    public void stopCommunication() throws Exception{
      PIPE.close();
    }

    @Override
    public String getName() {
        return "NamedPipeCommunicator";
    }

    @Override
    public String getShortName() {
        return "np";
    }

    @Override
    public String getDescription() {
        return "Uses named pipe to share data should use pipe:"+ NamedPipeServerCommunicator.PIPE_NAME;
    }

    @Override
    public Class<?> getServerClass() {
        return NamedPipeServerCommunicator.class;
    }




}
