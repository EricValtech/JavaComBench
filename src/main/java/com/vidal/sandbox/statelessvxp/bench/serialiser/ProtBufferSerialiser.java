package com.vidal.sandbox.statelessvxp.bench.serialiser;

import com.vidal.sandbox.statelessvxp.pojo.protbufgen.PackcontainerProt;

import java.io.ByteArrayOutputStream;

public class ProtBufferSerialiser extends BenchSerialiser {

    private final PackcontainerProt.PackContainer.Builder pcBuilder= PackcontainerProt.PackContainer.newBuilder();
    private final PackcontainerProt.Pack.Builder pBuilder= PackcontainerProt.Pack.newBuilder();
    @Override
    public void warmup() throws Exception {
        doBench(1);
    }




    @Override
    public Object doBench(Integer nbPojo) throws Exception {
        PackcontainerProt.PackContainer.Builder builder= PackcontainerProt.PackContainer.newBuilder();

        Iterable<? extends PackcontainerProt.Pack> toto =(Iterable<? extends PackcontainerProt.Pack>)getFactory().newInstance().createProtBuff(nbPojo);
        builder.addAllList(toto);
        PackcontainerProt.PackContainer bean = builder.build();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bean.writeTo(out);
        out.close();

        PackcontainerProt.PackContainer readBean = PackcontainerProt.PackContainer.parseFrom(out.toByteArray());
        return out.toByteArray().length;
    }

    @Override
    public String getName() {
        return "ProtocolBufferSerialiser";
    }

    @Override
    public String getShortName() {
        return "pb";
    }

    @Override
    public String getDescription() {
        return "uses Protocol Buffer 2.5 with a '.proto' file (@see ClassToProtBuff)";
    }
}
