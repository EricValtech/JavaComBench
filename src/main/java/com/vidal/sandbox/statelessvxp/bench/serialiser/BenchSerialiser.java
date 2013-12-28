package com.vidal.sandbox.statelessvxp.bench.serialiser;


import com.vidal.sandbox.statelessvxp.bench.Bench;

public abstract class BenchSerialiser extends Bench {

    public abstract void warmup() throws Exception;
    public abstract Object doBench(Integer nbPojo) throws Exception;


}
