package com.vidal.sandbox.statelessvxp.comunicator;

public class SocketCommunicator extends BenchCommunicator {
    @Override
    public Object doBench(Integer nbPojo) throws Exception {
        return null;
    }

    @Override
    public String getName() {
        return "SocketCommunicator";
    }

    @Override
    public String getShortName() {
        return "sc";
    }

    @Override
    public String getDescription() {
        return "Uses socket to share objects";
    }

    @Override
    public Class<?> getServerClass() {
        return SocketServerCommunicator.class;
    }
}
