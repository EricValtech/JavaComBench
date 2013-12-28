package com.vidal.sandbox.statelessvxp;

import com.vidal.sandbox.statelessvxp.bench.comunicator.MappedFileCommunicator;
import com.vidal.sandbox.statelessvxp.pojo.factory.PackFactory;
import org.junit.Test;

public class MappedFileCommunicatorTest {
	@Test
	public void testLaunchStopServer() throws Exception {
        MappedFileCommunicator mfc = new MappedFileCommunicator();
        mfc.setFactory(PackFactory.class);
        mfc.startServer();
        mfc.startCommunication();
        mfc.initObjects(1);
        mfc.MEM.rewind();
        mfc.MEM.putInt(100);
        Thread.sleep(1000);
        mfc.stopCommunication();
        mfc.stopServer();
	}
}
