package com.api.rest.api.questions.model;

import java.util.List;

/**
* rathr1
* 
**/
public class Consumers {
	private List<Addresses> addresses;

    private String birthDate;

    private String consumerGuid;

    private String email;

    private boolean emailVerified;

    private String firstName;

    private String genderCode;

    private String lastName;

    private String maritalStatusCode;

    private List<Members> members;

    private String middleName;

    private List<Phones> phones;

    public void setAddresses(List<Addresses> addresses){
        this.addresses = addresses;
    }
    public List<Addresses> getAddresses(){
        return this.addresses;
    }
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }
    public String getBirthDate(){
        return this.birthDate;
    }
    public void setConsumerGuid(String consumerGuid){
        this.consumerGuid = consumerGuid;
    }
    public String getConsumerGuid(){
        return this.consumerGuid;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmailVerified(boolean emailVerified){
        this.emailVerified = emailVerified;
    }
    public boolean getEmailVerified(){
        return this.emailVerified;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public void setGenderCode(String genderCode){
        this.genderCode = genderCode;
    }
    public String getGenderCode(){
        return this.genderCode;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public void setMaritalStatusCode(String maritalStatusCode){
        this.maritalStatusCode = maritalStatusCode;
    }
    public String getMaritalStatusCode(){
        return this.maritalStatusCode;
    }
    public void setMembers(List<Members> members){
        this.members = members;
    }
    public List<Members> getMembers(){
        return this.members;
    }
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    public String getMiddleName(){
        return this.middleName;
    }
    public void setPhones(List<Phones> phones){
        this.phones = phones;
    }
    public List<Phones> getPhones(){
        return this.phones;
    }
}
