package com.api.rest.api.questions.model;
/**
* rathr1
* 
**/
public class Phones {
	private String extension;

    private String name;

    private String phoneGuid;

    private String phoneNo;

    private boolean primary;

    private String type;

    public void setExtension(String extension){
        this.extension = extension;
    }
    public String getExtension(){
        return this.extension;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPhoneGuid(String phoneGuid){
        this.phoneGuid = phoneGuid;
    }
    public String getPhoneGuid(){
        return this.phoneGuid;
    }
    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }
    public String getPhoneNo(){
        return this.phoneNo;
    }
    public void setPrimary(boolean primary){
        this.primary = primary;
    }
    public boolean getPrimary(){
        return this.primary;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
