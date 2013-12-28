package com.vidal.sandbox.statelessvxp.pojo;

import java.util.Collection;


public abstract class PojoFactory <T,K> {

    private Class<T> pojoClass;
    private Class<K> pojoProtBuffClass;

    public abstract Collection<T> create(int nbPojo);
    public abstract Collection<K> createProtBuff(int nbPojo);
    public Class<T> getPojoClass() {
        if(pojoClass!=null) return pojoClass;
        Collection<T> dummyList = this.create(1);
        if (! dummyList.iterator().hasNext()) { return null;}
        setPojoClass((Class<T>) dummyList.iterator().next().getClass());
        return pojoClass;
    }


    private void setPojoClass(Class<T> pojoClass) {
        this.pojoClass = pojoClass;
    }

    public Class<K> getPojoProtBuffClass() {
        return pojoProtBuffClass;
    }

    public void setPojoProtBuffClass(Class<K> pojoProtBuffClass) {
        this.pojoProtBuffClass = pojoProtBuffClass;
    }
}
