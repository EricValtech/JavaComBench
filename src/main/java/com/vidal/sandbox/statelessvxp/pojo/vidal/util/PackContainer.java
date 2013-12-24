package com.vidal.sandbox.statelessvxp.pojo.vidal.util;

import java.util.ArrayList;
import java.util.List;

import org.msgpack.annotation.Message;

import com.vidal.sandbox.statelessvxp.pojo.vidal.Pack;

@Message
public class PackContainer {
	private List<Pack> list = new ArrayList<Pack>();

    public List<Pack> getList() {
        return list;
    }

    public void setList(List<Pack> list) {
        this.list = list;
    }
}
