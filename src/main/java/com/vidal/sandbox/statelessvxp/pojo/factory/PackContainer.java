package com.vidal.sandbox.statelessvxp.pojo.factory;

import com.vidal.sandbox.statelessvxp.pojo.vidal.Pack;
import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Message
public class PackContainer implements Serializable{
	private List<Pack> list = new ArrayList<Pack>();

    public List<Pack> getList() {
        return list;
    }

    public void setList(List<Pack> list) {
        this.list = list;
    }
}
