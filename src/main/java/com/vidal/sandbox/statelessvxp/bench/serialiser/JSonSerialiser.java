package com.vidal.sandbox.statelessvxp.bench.serialiser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collection;

public class JSonSerialiser extends BenchSerialiser {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void warmup() throws Exception {
        OutputStream str = new ByteArrayOutputStream();
        mapper.writeValue(str,getFactory().newInstance().create(1));
    }

    @Override
    public Object doBench(Integer nbPojo) throws Exception {

        Object res=getToSerialise();
        if (res instanceof Collection) res = ((Collection)res).iterator().next();

         //Seriallize
        OutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out,res);
        // Deserialize
        Object read = mapper.readValue(out.toString().getBytes(), getFactory().newInstance().getPojoClass());
        return out.toString().length();
    }

    @Override
    public String getName() {
        return "JSonSerialiser";
    }

    @Override
    public String getShortName() {
        return "json";
    }

    @Override
    public String getDescription() {
        return "uses jackson 2 fast implementation for JSON";
    }
}
