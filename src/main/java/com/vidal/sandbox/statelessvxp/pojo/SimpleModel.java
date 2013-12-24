package com.vidal.sandbox.statelessvxp.pojo;

import org.msgpack.annotation.Message;

@Message
public class SimpleModel {
	  // public fields are serialized.
    private String name="NAME";
    private double version=0.6;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
}
