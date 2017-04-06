package com.rcl.excalibur.data.service.request.guest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateAccountRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String brand;
    @SerializedName("termsAndConditionsAgreement")
    @Expose
    private TermsAndConditionsAgreementRequest termsAndConditionsAgreement;
    @SerializedName("securityQuestions")
    @Expose
    private List<SecurityQuestionRequest> securityQuestions = null;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public TermsAndConditionsAgreementRequest getTermsAndConditionsAgreement() {
        return termsAndConditionsAgreement;
    }

    public void setTermsAndConditionsAgreement(TermsAndConditionsAgreementRequest termsAndConditionsAgreement) {
        this.termsAndConditionsAgreement = termsAndConditionsAgreement;
    }

    public List<SecurityQuestionRequest> getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(List<SecurityQuestionRequest> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }
}

