package com.vidal.sandbox.statelessvxp;

import com.vidal.sandbox.statelessvxp.bench.comunicator.NamedPipeCommunicator;
import com.vidal.sandbox.statelessvxp.pojo.factory.PackFactory;
import org.junit.Test;

public class NamedPipeCommunicatorTest {
	@Test
	public void testLaunchStopServer() throws Exception {
        NamedPipeCommunicator npc = new NamedPipeCommunicator();
        npc.setFactory(PackFactory.class);
        npc.startServer();
        npc.startCommunication();
        npc.initObjects(1);
        npc.PIPE.write("TEST".getBytes());
        int size= npc.PIPE.readInt();
        System.out.println("SIZE : "+size);
        Thread.sleep(1000);
        npc.stopCommunication();
        npc.stopServer();
	}
}
