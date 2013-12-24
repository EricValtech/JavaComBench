package com.vidal.sandbox.statelessvxp.pojo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.msgpack.annotation.Message;

@Message
public class FullTypesModel {

	// simple Types
    public int intType=10;
    public double doubleType=12;
    public boolean boolType=true;

    // Common objects
    public String stringOBJ="KLKDQSKDSMLK";
    public Boolean booleanOBJ=Boolean.TRUE;
    
    //Custom object/enum
    public SimpleEnum enumCOBJ = SimpleEnum.ENUM2;
    public SimpleModel simpleCOBJ = new SimpleModel();
     
    // Arrays
    public int [] arrayType={1,2};
    public String [] arrayOBJ={"S1","S2"};
    public SimpleEnum [] arrayENUM={SimpleEnum.ENUM2,SimpleEnum.ENUM1};
    public SimpleModel [] arrayCOBJ={new SimpleModel(),new SimpleModel()};
    
    //Collection
    public List<Integer> collListOBJ;
    public List<SimpleEnum> collListEnum;
    public List<SimpleModel> collListCOBJ;
    
    
    
    public FullTypesModel() {
    	//fill collections
    	collListOBJ = new ArrayList<Integer>();
    	collListOBJ.add(1);collListOBJ.add(2);collListOBJ.add(3);collListOBJ.add(4);
    	
    	collListEnum = new  LinkedList<SimpleEnum>();
    	collListEnum.add(SimpleEnum.ENUM1);collListEnum.add(SimpleEnum.ENUM2);
    	
    	collListCOBJ =  new ArrayList<SimpleModel>();
    	collListCOBJ.add(new SimpleModel());collListCOBJ.add(new SimpleModel());
    	
    }



	public int getIntType() {
		return intType;
	}



	public void setIntType(int intType) {
		this.intType = intType;
	}



	public double getDoubleType() {
		return doubleType;
	}



	public void setDoubleType(double doubleType) {
		this.doubleType = doubleType;
	}



	public boolean isBoolType() {
		return boolType;
	}



	public void setBoolType(boolean boolType) {
		this.boolType = boolType;
	}



	public String getStringOBJ() {
		return stringOBJ;
	}



	public void setStringOBJ(String stringOBJ) {
		this.stringOBJ = stringOBJ;
	}



	public Boolean getBooleanOBJ() {
		return booleanOBJ;
	}



	public void setBooleanOBJ(Boolean booleanOBJ) {
		this.booleanOBJ = booleanOBJ;
	}



	public SimpleEnum getEnumCOBJ() {
		return enumCOBJ;
	}



	public void setEnumCOBJ(SimpleEnum enumCOBJ) {
		this.enumCOBJ = enumCOBJ;
	}



	public SimpleModel getSimpleCOBJ() {
		return simpleCOBJ;
	}



	public void setSimpleCOBJ(SimpleModel simpleCOBJ) {
		this.simpleCOBJ = simpleCOBJ;
	}


	public int[] getArrayType() {
		return arrayType;
	}



	public void setArrayType(int[] arrayType) {
		this.arrayType = arrayType;
	}



	public String[] getArrayOBJ() {
		return arrayOBJ;
	}



	public void setArrayOBJ(String[] arrayOBJ) {
		this.arrayOBJ = arrayOBJ;
	}



	public SimpleEnum[] getArrayENUM() {
		return arrayENUM;
	}



	public void setArrayENUM(SimpleEnum[] arrayENUM) {
		this.arrayENUM = arrayENUM;
	}



	public SimpleModel[] getArrayCOBJ() {
		return arrayCOBJ;
	}



	public void setArrayCOBJ(SimpleModel[] arrayCOBJ) {
		this.arrayCOBJ = arrayCOBJ;
	}



	public List<Integer> getCollListOBJ() {
		return collListOBJ;
	}



	public void setCollListOBJ(List<Integer> collListOBJ) {
		this.collListOBJ = collListOBJ;
	}



	public List<SimpleEnum> getCollListEnum() {
		return collListEnum;
	}



	public void setCollListEnum(List<SimpleEnum> collListEnum) {
		this.collListEnum = collListEnum;
	}



	public List<SimpleModel> getCollListCOBJ() {
		return collListCOBJ;
	}



	public void setCollListCOBJ(List<SimpleModel> collListCOBJ) {
		this.collListCOBJ = collListCOBJ;
	}





}
