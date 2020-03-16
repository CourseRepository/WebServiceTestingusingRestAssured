package com.api.rest.api.questions.model;

import java.util.List;

/**
* rathr1
* 
**/
public class Root {
	private List<Cashcard> cashcard;

    private List<Consumers> consumers;

    private List<Credentials> credentials;

    private String guid;

    public void setCashcard(List<Cashcard> cashcard){
        this.cashcard = cashcard;
    }
    public List<Cashcard> getCashcard(){
        return this.cashcard;
    }
    public void setConsumers(List<Consumers> consumers){
        this.consumers = consumers;
    }
    public List<Consumers> getConsumers(){
        return this.consumers;
    }
    public void setCredentials(List<Credentials> credentials){
        this.credentials = credentials;
    }
    public List<Credentials> getCredentials(){
        return this.credentials;
    }
    public void setGuid(String guid){
        this.guid = guid;
    }
    public String getGuid(){
        return this.guid;
    }
}
