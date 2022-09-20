package me.whiteship.refactoring._16_temporary_field._36_introduce_special_case.done;

/**
 * 특이 케이스 처리할 클래스
 */
public class UnknownCustomer extends Customer {
    public UnknownCustomer() {
        super("unknown", new BasicBillingPlan(), new NullPaymentHistory());
    }

    @Override
    public boolean isUnknown() {
        return true;
    }

    @Override
    public String getName() {
        return "occupant";
    }


}
