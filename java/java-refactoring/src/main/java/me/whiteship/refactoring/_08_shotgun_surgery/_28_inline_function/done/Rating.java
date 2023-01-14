package me.whiteship.refactoring._08_shotgun_surgery._28_inline_function.done;

public class Rating {

    public int rating(Driver driver) {
        // 늦게 배달한 횟수가 5 이상 여부
        return driver.getNumberOfLateDeliveries() > 5 ? 2 : 1;
    }

//    private boolean moreThanFiveLateDeliveries(Driver driver) {
//        // 늦게 배달한 횟수가 5 이상 여부
//        return driver.getNumberOfLateDeliveries() > 5;
//    }
}
