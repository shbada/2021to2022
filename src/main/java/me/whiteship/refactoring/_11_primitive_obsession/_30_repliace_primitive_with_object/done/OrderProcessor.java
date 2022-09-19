package me.whiteship.refactoring._11_primitive_obsession._30_repliace_primitive_with_object.done;

import java.util.List;

public class OrderProcessor {

    public long numberOfHighPriorityOrders(List<Order> orders) {
        return orders.stream()
                // 기본형
                .filter(o -> o.getPriority().higherThan(new Priority("normal")))
                .count();
    }
}
