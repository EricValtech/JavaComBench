package com.vidal.sandbox.statelessvxp.bench.comunicator;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerCommunicator {
    public static final int PORT_NUMBER= 4444;

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            clientSocket.getOutputStream().write((""+inputLine.length()).getBytes());
        }
    }
}
