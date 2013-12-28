package com.vidal.sandbox.statelessvxp;

import com.vidal.sandbox.statelessvxp.pojo.FullTypesModel;
import com.vidal.sandbox.statelessvxp.pojo.SimpleModel;
import org.junit.Test;
import org.msgpack.MessagePack;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple App.
 */
public class MessagePackTest {
	@Test
	public void testSimple() throws IOException {
		SimpleModel src = new SimpleModel();

		MessagePack msgpack = new MessagePack();
		
		byte[] bytes = msgpack.write(src);
		SimpleModel dst = msgpack.read(bytes, SimpleModel.class);
		
		assertNotNull(dst);
		assertEquals(src.getName(), dst.getName());
		assertEquals(src.getVersion(), dst.getVersion(), 0.01f);

	}

	@Test
	public void testAllTypes() throws IOException {
		FullTypesModel src = new FullTypesModel();

		MessagePack msgpack = new MessagePack();
		// Serialize
		byte[] bytes = msgpack.write(src);
		// Deserialize
		FullTypesModel dst = msgpack.read(bytes, FullTypesModel.class);
		
		assertNotNull(dst);
		assertEquals(dst.getIntType(), src.getIntType());
		assertNotNull(dst.getArrayCOBJ());
		assertEquals(dst.getArrayCOBJ().length, src.getArrayCOBJ().length);
		assertEquals(dst.getBooleanOBJ(), src.getBooleanOBJ());
		assertNotNull(dst.getCollListCOBJ());
		assertEquals(dst.getCollListCOBJ().size(), src.getCollListCOBJ().size());
		
	}
}
