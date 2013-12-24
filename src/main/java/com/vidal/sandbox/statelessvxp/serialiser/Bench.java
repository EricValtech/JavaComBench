package com.vidal.sandbox.statelessvxp.serialiser;

import java.io.IOException;

public interface Bench {
    Object doBench(Integer nbPojo) throws Exception;
    public  String getName();
    public  String getShortName();
    public  String getDescription();
}
