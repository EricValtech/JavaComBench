package com.vidal.sandbox.statelessvxp.bench.serialiser;

@SuppressWarnings("ALL")
public class ProtStuffSerialiser  {


    //@Override
    public void warmup() throws Exception {
        doBench(1);
    }




    //@Override
    public Object doBench(Integer nbPojo) throws Exception {
        // NOT WORKING FOR PACK (pojo) ERROR IN DESERIALISATION... so comment it... ;(
        /*PackContainer src = new PackFactory().create(nbPojo).iterator().next();

        Schema<PackContainer> schema = RuntimeSchema.getSchema(PackContainer.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(100*1024*1024);
        byte[] protostuff ;
        try {
            protostuff= ProtostuffIOUtil.toByteArray(src, schema, buffer);
        }
        finally {
            buffer.clear();
        }
        PackContainer dst = schema.newMessage();
        ProtobufIOUtil.mergeFrom(protostuff, dst, schema);
        return protostuff.length;*/
        return -1;
    }

    //@Override
    public String getName() {
        return "ProtoStuffSerialiser";
    }

    //@Override
    public String getShortName() {
        return "ps";
    }

    //@Override
    public String getDescription() {
        return "uses ProtoStuff to generate schema from Pojo";
    }
}
