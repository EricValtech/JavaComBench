package com.vidal.sandbox.statelessvxp;

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.vidal.sandbox.statelessvxp.pojo.FullTypesModel;
import com.vidal.sandbox.statelessvxp.pojo.SimpleModel;

/**
 * Unit test for simple App.
 */
public class ProtStuffTest {
	@Test
	public void testSimple() throws IOException {
		SimpleModel src = new SimpleModel();
		
		Schema<SimpleModel> schema = RuntimeSchema.getSchema(SimpleModel.class);
    	LinkedBuffer buffer = LinkedBuffer.allocate(5120); 
    	byte[] protostuff ;
    	try {
    		protostuff= ProtostuffIOUtil.toByteArray(src, schema, buffer);
    	}
    	finally {
    		buffer.clear();
    	}
    	SimpleModel dst = schema.newMessage();
    	ProtobufIOUtil.mergeFrom(protostuff, dst, schema);
		assertNotNull(dst);
		assertEquals(src.getName(), dst.getName());
		assertEquals(src.getVersion(), dst.getVersion(), 0.000001);
	}

	@Test
	public void testAllTypes() throws IOException {
		FullTypesModel src = new FullTypesModel();
		Schema<FullTypesModel> schema = RuntimeSchema.getSchema(FullTypesModel.class);
	   	LinkedBuffer buffer = LinkedBuffer.allocate(50120); 
	   	byte[] protostuff ;
    	try {
    		protostuff= ProtobufIOUtil.toByteArray(src, schema, buffer);
    	}
    	finally {
    		buffer.clear();
    	}
		// Deserialize
		FullTypesModel dst = schema.newMessage();
		ProtobufIOUtil.mergeFrom(protostuff, dst, schema);
		
		assertNotNull(dst);
		assertEquals(dst.getIntType(), src.getIntType());
		assertNotNull(dst.getArrayCOBJ());
		assertEquals(dst.getArrayCOBJ().length, src.getArrayCOBJ().length);
		assertEquals(dst.getBooleanOBJ(), src.getBooleanOBJ());
		assertNotNull(dst.getCollListCOBJ());
		//assertEquals(dst.getCollListCOBJ().size(), src.getCollListCOBJ().size());
		
	}
}
