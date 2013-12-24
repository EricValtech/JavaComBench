package com.vidal.sandbox.statelessvxp.serialiser;


import java.io.IOException;

public interface BenchSerialiser  extends  Bench{
    void warmup() throws IOException;
}
