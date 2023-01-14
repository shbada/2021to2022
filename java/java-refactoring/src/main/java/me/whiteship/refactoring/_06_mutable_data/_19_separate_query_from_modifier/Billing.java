package me.whiteship.refactoring._06_mutable_data._19_separate_query_from_modifier;

public class Billing {

    private Customer customer;

    private EmailGateway emailGateway;

    public Billing(Customer customer, EmailGateway emailGateway) {
        this.customer = customer;
        this.emailGateway = emailGateway;
    }

    /**
     * 두개의 책임을 분리
     * @return
     */
    public double getTotalOutstandingAndSendBill() {
        // 1. 계산
        double result = customer.getInvoices().stream()
                .map(Invoice::getAmount)
                .reduce((double) 0, Double::sum);

        // 2. sendBill
        sendBill();
        return result;
    }

    /**
     * public 으로 변경
     */
    public void sendBill() {
        emailGateway.send(formatBill(customer));
    }

    private String formatBill(Customer customer) {
        return "sending bill for " + customer.getName();
    }
}
