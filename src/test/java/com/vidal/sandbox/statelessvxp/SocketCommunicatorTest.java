package com.vidal.sandbox.statelessvxp;

import com.vidal.sandbox.statelessvxp.comunicator.SocketCommunicator;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SocketCommunicatorTest {
	@Test
	public void testLaunchStopServer() throws IOException {
        SocketCommunicator so = new SocketCommunicator();
        so.startServer();
        so.stopServer();
	}
}
