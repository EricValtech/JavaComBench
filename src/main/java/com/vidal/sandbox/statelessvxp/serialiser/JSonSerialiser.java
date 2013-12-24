package com.vidal.sandbox.statelessvxp.serialiser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackContainer;
import com.vidal.sandbox.statelessvxp.pojo.vidal.util.PackFactory;

import java.io.*;

public class JSonSerialiser implements BenchSerialiser {

    //WARNING getters withour setters must be marked..

    private static final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void warmup() throws IOException {
        OutputStream str = new ByteArrayOutputStream();
        mapper.writeValue(str,PackFactory.createPackList(10, 10000));
    }

    @Override
    public Object doBench(Integer nbPojo) throws IOException {
        PackContainer pc = PackFactory.createPackList(nbPojo, 100);

        //Seriallize
        OutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out,pc);
        // Deserialize
        PackContainer read = mapper.readValue(out.toString().getBytes(),PackContainer.class);
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
