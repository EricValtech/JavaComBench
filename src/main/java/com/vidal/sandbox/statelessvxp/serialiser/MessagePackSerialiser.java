package com.vidal.sandbox.statelessvxp.serialiser;

import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackContainer;
import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackFactory;
import org.msgpack.MessagePack;

import java.io.IOException;

public class MessagePackSerialiser implements BenchSerialiser {

    private static final MessagePack msgpack = new MessagePack();

    @Override
    public void warmup() throws IOException {
        // do a dummy serialisation to init the stuff..
        msgpack.write(PackFactory.createPackList(10,10000));
    }

    @Override
    public Object doBench(Integer nbPojo) throws IOException {
        PackContainer pc = PackFactory.createPackList(nbPojo, 100);
        // Serialize
        byte[] bytes = msgpack.write(pc);
        // Deserialize
        PackContainer dst = msgpack.read(bytes, PackContainer.class);
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
