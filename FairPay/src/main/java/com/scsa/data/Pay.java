package com.scsa.data;

public class Pay {

    private String paymentId;
    private String claimee_accountNumber;

    public Pay() {
        super();
    }

    public Pay(String paymentId, String claimee_accountNumber) {
        this.paymentId = paymentId;
        this.claimee_accountNumber = claimee_accountNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getClaimee_accountNumber() {
        return claimee_accountNumber;
    }

    public void setClaimee_accountNumber(String claimee_accountNumber) {
        this.claimee_accountNumber = claimee_accountNumber;
    }
}
