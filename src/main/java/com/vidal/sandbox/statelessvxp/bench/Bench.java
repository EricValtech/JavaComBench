package com.vidal.sandbox.statelessvxp.bench;

import com.vidal.sandbox.statelessvxp.pojo.PojoFactory;

public abstract class Bench {
    private Class <? extends PojoFactory> factory = null;
    private Object toSerialise = null;

    public  abstract String getName();
    public  abstract String getShortName();
    public  abstract String getDescription();

    public void initObjects(int nbObj) throws Exception{
        if(getFactory()==null) { throw new RuntimeException("ERROR: Factory not set..."); }
        PojoFactory factory = getFactory().newInstance();
        setToSerialise(factory.create(nbObj));
    }

    public Class<? extends PojoFactory> getFactory() {
        return factory;
    }

    public void setFactory(Class<? extends PojoFactory> factory) {
        this.factory = factory;
    }

    public Object getToSerialise() {
        return toSerialise;
    }

    protected void setToSerialise(Object toSerialise) {
        this.toSerialise = toSerialise;
    }
}
