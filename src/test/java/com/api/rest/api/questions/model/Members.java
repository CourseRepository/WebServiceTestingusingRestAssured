package com.api.rest.api.questions.model;
/**
* rathr1
* 
**/
public class Members {
	private String altAccountIdent;

    private String code;

    private String familyPosition;

    private String insuredNo;

    private String memberNo;

    private String relationshipCode;

    private String xRefMemberNo;

    public void setAltAccountIdent(String altAccountIdent){
        this.altAccountIdent = altAccountIdent;
    }
    public String getAltAccountIdent(){
        return this.altAccountIdent;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setFamilyPosition(String familyPosition){
        this.familyPosition = familyPosition;
    }
    public String getFamilyPosition(){
        return this.familyPosition;
    }
    public void setInsuredNo(String insuredNo){
        this.insuredNo = insuredNo;
    }
    public String getInsuredNo(){
        return this.insuredNo;
    }
    public void setMemberNo(String memberNo){
        this.memberNo = memberNo;
    }
    public String getMemberNo(){
        return this.memberNo;
    }
    public void setRelationshipCode(String relationshipCode){
        this.relationshipCode = relationshipCode;
    }
    public String getRelationshipCode(){
        return this.relationshipCode;
    }
    public void setXRefMemberNo(String xRefMemberNo){
        this.xRefMemberNo = xRefMemberNo;
    }
    public String getXRefMemberNo(){
        return this.xRefMemberNo;
    }
}
