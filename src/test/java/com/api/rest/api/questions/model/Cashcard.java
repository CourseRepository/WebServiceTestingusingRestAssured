package com.api.rest.api.questions.model;
/**
* rathr1
* 
**/
public class Cashcard {
	private String bin;

    private String cashCardNo;

    private String groupNO;

    private int id;

    private String pcn;

    public void setBin(String bin){
        this.bin = bin;
    }
    public String getBin(){
        return this.bin;
    }
    public void setCashCardNo(String cashCardNo){
        this.cashCardNo = cashCardNo;
    }
    public String getCashCardNo(){
        return this.cashCardNo;
    }
    public void setGroupNO(String groupNO){
        this.groupNO = groupNO;
    }
    public String getGroupNO(){
        return this.groupNO;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPcn(String pcn){
        this.pcn = pcn;
    }
    public String getPcn(){
        return this.pcn;
    }

}
