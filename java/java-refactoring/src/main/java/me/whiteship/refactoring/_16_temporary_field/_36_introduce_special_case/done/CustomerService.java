package me.whiteship.refactoring._16_temporary_field._36_introduce_special_case.done;

/**
 * 주요 클래스
 */
public class CustomerService {

    public String customerName(Site site) {
        // site 가 이미 알려주고 잇는 정보다.
//        Customer customer = site.getCustomer();
//
//        String customerName;
//        if (customer.isUnknown()) { // 이름이 unknown 이면
//            customerName = "occupant";
//        } else {
//            customerName = customer.getName();
//        }

        return site.getCustomer().getName();
    }

    public BillingPlan billingPlan(Site site) {
       return site.getCustomer().getBillingPlan();
    }

    // 1) 메서드 추출
    // 2) 여기에 위치할 메서드가 맞나? (Customer의 정보를 가지고 판단하고 있으므로 Customer로 옮기는게 나아보인다.)
//    private static boolean isUnknown(Customer customer) {
//        return customer.getName().equals("unknown");
//    }

    public int weeksDelinquent(Site site) {
        return site.getCustomer().getPaymentHistory().getWeeksDelinquentInLastYear();
    }

}
