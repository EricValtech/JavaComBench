package com.vidal.sandbox.statelessvxp.bench.comunicator;

import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

import java.net.Socket;

public class SocketCommunicator extends BenchCommunicator {
    private static Socket SOCKET;



    @Override
    public void startCommunication() throws Exception {
        if (getServerProcess() == null) { throw new RuntimeException("Server is down.. can't continue.."); }
        SOCKET= new Socket("localhost", SocketServerCommunicator.PORT_NUMBER);
        SOCKET.getOutputStream().write("OK".getBytes());
    }

    @Override
    public Object doBench(Class<? extends PojoFactory> factory, Integer nbPojo) throws Exception {
        if (getServerProcess() == null) { throw new RuntimeException("Server is down.. can't continue.."); }
        if (! SOCKET.isConnected()) { throw new RuntimeException("Socket is not connected (did you call init?).."); }
        SOCKET.getOutputStream().write(getToSerialise().toString().getBytes());
        int resSize = SOCKET.getInputStream().available();
        byte[] resBytes = new byte[resSize];
        SOCKET.getInputStream().read(resBytes);
        int sizeFromServer=-1;
        try {
            sizeFromServer=  Integer.parseInt(new String(resBytes));
        }
        catch(NumberFormatException e) {/*do nothink*/}

       return sizeFromServer;
    }

    @Override
    public void stopCommunication() throws Exception{
        if (SOCKET.isConnected()) {
            SOCKET.close();
        }
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
