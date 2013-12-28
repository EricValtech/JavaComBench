package com.vidal.sandbox.statelessvxp.bench.serialiser;

import org.msgpack.MessagePack;

import java.util.Collection;

public class MessagePackSerialiser extends BenchSerialiser {

    private static final MessagePack msgpack = new MessagePack();

    @Override
    public void warmup() throws Exception {
        // do a dummy serialisation to init the stuff..
        msgpack.write(getFactory().newInstance().create(1));
    }

    @Override
    public Object doBench(Integer nbPojo) throws Exception {
         // Serialize
        Object res=getToSerialise();
        if (res instanceof Collection) res = ((Collection)res).iterator().next();
        byte[] bytes = msgpack.write(res);
        // Deserialize
        Object dst = msgpack.read(bytes, getFactory().newInstance().getPojoClass());
        return bytes.length;
    }

    @Override
    public String getName() {
        return "MessagePackSerialiser";
    }

    @Override
    public String getShortName() {
        return "mp";
    }

    @Override
    public String getDescription() {
        return "uses MessagePack version 0.6.8 with annotations on Models";
    }

}
