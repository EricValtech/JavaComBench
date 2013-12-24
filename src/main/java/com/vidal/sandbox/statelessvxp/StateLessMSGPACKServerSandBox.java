package com.vidal.sandbox.statelessvxp;

import java.net.ServerSocket;
import java.net.Socket;

import org.msgpack.MessagePack;

import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackContainer;

public class StateLessMSGPACKServerSandBox {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(10007); 
	    Socket clientSocket = null; 
	    System.out.println ("Waiting for connection.....");
	   
	    
		MessagePack msgpack = new MessagePack();
	    System.out.println ("Connection successful");
	    while(new Integer(1)==1) {
	    	clientSocket = serverSocket.accept(); 
		    System.out.println ("Waiting for input.....");
	
		    PackContainer dst = msgpack.read(clientSocket.getInputStream(), PackContainer.class);
	         System.out.println ("Server: " + dst.getList().size());

		  
	    }
	    clientSocket.close(); 
	    serverSocket.close(); 
	}

}
