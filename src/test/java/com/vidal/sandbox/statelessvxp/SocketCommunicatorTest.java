package com.vidal.sandbox.statelessvxp;

import com.vidal.sandbox.statelessvxp.bench.comunicator.SocketCommunicator;
import org.junit.Test;

import java.io.IOException;

public class SocketCommunicatorTest {
	@Test
	public void testLaunchStopServer() throws IOException {
        SocketCommunicator so = new SocketCommunicator();
        so.startServer();
        so.stopServer();
	}
}
