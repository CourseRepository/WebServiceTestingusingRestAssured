package com.api.rest.api.questions.model;
/**
* rathr1
* 
**/
public class Credentials {
	private String credentialType;

    private String credentialValue;

    private boolean obsolete;

    public void setCredentialType(String credentialType){
        this.credentialType = credentialType;
    }
    public String getCredentialType(){
        return this.credentialType;
    }
    public void setCredentialValue(String credentialValue){
        this.credentialValue = credentialValue;
    }
    public String getCredentialValue(){
        return this.credentialValue;
    }
    public void setObsolete(boolean obsolete){
        this.obsolete = obsolete;
    }
    public boolean getObsolete(){
        return this.obsolete;
    }
}
