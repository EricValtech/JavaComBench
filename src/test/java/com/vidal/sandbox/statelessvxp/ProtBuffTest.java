package com.vidal.sandbox.statelessvxp;

import com.vidal.sandbox.statelessvxp.pojo.protbufgen.FulltypesmodelProt;
import com.vidal.sandbox.statelessvxp.pojo.protbufgen.PackcontainerProt;
import com.vidal.sandbox.statelessvxp.pojo.protbufgen.SimplemodelProt;
import com.vidal.sandbox.statelessvxp.serialiser.ProtBufferSerialiser;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class ProtBuffTest {
	@Test
	public void testSimple() throws IOException {
        SimplemodelProt.SimpleModel.Builder builder= SimplemodelProt.SimpleModel.newBuilder();
        builder.setName("NAME");
        builder.setVersion(12.0f);

        SimplemodelProt.SimpleModel bean = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bean.writeTo(out);
        out.close();

        SimplemodelProt.SimpleModel.Builder readBean = builder.mergeFrom(out.toString().getBytes());
        assertEquals(bean.getName(), readBean.getName());
        assertEquals(bean.getVersion(), readBean.getVersion(), 0.001f);
	}

	@Test
	public void testAllTypes() throws IOException {
        FulltypesmodelProt.SimpleModel.Builder simpleModelBuilder=FulltypesmodelProt.SimpleModel.newBuilder();
        simpleModelBuilder.setName("SimpleName");
        simpleModelBuilder.setVersion(10f);
        FulltypesmodelProt.SimpleModel simpleModel = simpleModelBuilder.build();
        FulltypesmodelProt.FullTypesModel.Builder builder = FulltypesmodelProt.FullTypesModel.newBuilder();
        builder.setBooleanOBJ(true);
        builder.setIntType(120);
        builder.setBoolType(true);
        builder.setDoubleType(12.0f);
        builder.setEnumCOBJ(FulltypesmodelProt.FullTypesModel.SimpleEnum.ENUM1);
        builder.setStringOBJ("bouhou");
        builder.setSimpleCOBJ(simpleModel);

        //fill one simple element
        builder.addAllArrayOBJ(Arrays.asList("S1","S2", "S3"));
        builder.addAllArrayType(Arrays.asList(new Long(10), new Long(20)));
        builder.addAllArrayCOBJ(Arrays.asList(simpleModel));

        FulltypesmodelProt.FullTypesModel bean = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bean.writeTo(out);
        out.close();
        builder.clear();
        FulltypesmodelProt.FullTypesModel.Builder readBean = builder.mergeFrom(out.toString().getBytes());

        assertNotNull(readBean);
        assertTrue(builder.getBooleanOBJ());
		assertEquals(readBean.getIntType(), bean.getIntType());
        assertEquals(readBean.getSimpleCOBJ().getName(), bean.getSimpleCOBJ().getName());
        assertNotNull(readBean.getArrayOBJList());
        assertEquals(readBean.getArrayOBJCount(), bean.getArrayOBJCount());
        assertEquals(readBean.getArrayOBJList().get(0), bean.getArrayOBJList().get(0));

		
	}


    @Test
    public void testPack() throws IOException {
        PackcontainerProt.PackContainer.Builder builder= PackcontainerProt.PackContainer.newBuilder();
        ProtBufferSerialiser ser = new ProtBufferSerialiser();
        Iterable<? extends PackcontainerProt.Pack> toto = ser.createPacks(10);
        builder.addAllList(toto);

        PackcontainerProt.PackContainer bean = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bean.writeTo(out);
        out.close();

        PackcontainerProt.PackContainer readBean = PackcontainerProt.PackContainer.parseFrom(out.toByteArray());

        assertNotNull(readBean);
        assertEquals(readBean.getListCount(), bean.getListCount());


    }
}
