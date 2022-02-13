package com.westssun.designpatterns._01_creational_patterns._04_builder._02_after;

import com.westssun.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;

/**
 * 인터페이스
 */
public interface TourPlanBuilder {

    // nights, days 는 함께 작성되야한다.
    TourPlanBuilder nightsAndDays(int nights, int days);

    // 클라이언트가 title()을 호출하면 builder 타입의 인스턴스를 받게되고, 여기서 제공하는 메서드를 사용한다.
    TourPlanBuilder title(String title);

    TourPlanBuilder startDate(LocalDate localDate);

    TourPlanBuilder whereToStay(String whereToStay);

    TourPlanBuilder addPlan(int day, String plan);

    TourPlanBuilder newInstance();

    // 여기에 도달할때까지 위의 builder 를 받아서 비즈니스 로직을 완성한다.
    TourPlan getPlan();

}
