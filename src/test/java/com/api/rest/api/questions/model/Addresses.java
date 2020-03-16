package com.api.rest.api.questions.model;
/**
* rathr1
* 
**/
public class Addresses {
	private String address1;

    private String address2;

    private String addressGuid;

    private String city;

    private String country;

    private int ePostId;

    private String name;

    private boolean primaryBilling;

    private boolean primaryShipping;

    private String state;

    private String type;

    private String zipcode;

    public void setAddress1(String address1){
        this.address1 = address1;
    }
    public String getAddress1(){
        return this.address1;
    }
    public void setAddress2(String address2){
        this.address2 = address2;
    }
    public String getAddress2(){
        return this.address2;
    }
    public void setAddressGuid(String addressGuid){
        this.addressGuid = addressGuid;
    }
    public String getAddressGuid(){
        return this.addressGuid;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setEPostId(int ePostId){
        this.ePostId = ePostId;
    }
    public int getEPostId(){
        return this.ePostId;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrimaryBilling(boolean primaryBilling){
        this.primaryBilling = primaryBilling;
    }
    public boolean getPrimaryBilling(){
        return this.primaryBilling;
    }
    public void setPrimaryShipping(boolean primaryShipping){
        this.primaryShipping = primaryShipping;
    }
    public boolean getPrimaryShipping(){
        return this.primaryShipping;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setZipcode(String zipcode){
        this.zipcode = zipcode;
    }
    public String getZipcode(){
        return this.zipcode;
    }
}
