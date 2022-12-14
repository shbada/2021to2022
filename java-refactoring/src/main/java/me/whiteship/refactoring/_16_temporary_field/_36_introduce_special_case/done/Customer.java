package me.whiteship.refactoring._16_temporary_field._36_introduce_special_case.done;

public class Customer {

    private String name;

    private BillingPlan billingPlan;

    private PaymentHistory paymentHistory;

    public Customer(String name, BillingPlan billingPlan, PaymentHistory paymentHistory) {
        this.name = name;
        this.billingPlan = billingPlan;
        this.paymentHistory = paymentHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BillingPlan getBillingPlan() {
        return billingPlan;
    }

    public void setBillingPlan(BillingPlan billingPlan) {
        this.billingPlan = billingPlan;
    }

    public PaymentHistory getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(PaymentHistory paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    // 1) 메서드 추출
    // 2) 여기에 위치할 메서드가 맞나? (Customer의 정보를 가지고 판단하고 있으므로 Customer로 옮기는게 나아보인다.)
    public boolean isUnknown() {
        return false;
    }
}
