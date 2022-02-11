package com.westssun.designpatterns._04_builder._02_after;

import com.westssun.designpatterns._04_builder._01_before.DetailPlan;
import com.westssun.designpatterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 인터페이스 구현체
 */
public class DefaultTourBuilder implements TourPlanBuilder {

    private String title;
    private int nights;
    private int days;
    private LocalDate startDate;
    private String whereToStay;
    private List<DetailPlan> plans;

    // 인스턴스 return
    private TourPlan tourPlan;

    @Override
    public TourPlanBuilder newInstance() {
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder nightsAndDays(int nights, int days) {
        // builder 사용시 계속해서 객체 생성을 막는다.
        // 대신 newInstance()를 호출해야한다.
        this.tourPlan.setNights(nights);

        this.nights = nights;
        this.days = days;
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        this.whereToStay = whereToStay;
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        if (this.plans == null) {
            this.plans = new ArrayList<>();
        }

        this.plans.add(new DetailPlan(day, plan));
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return new TourPlan(title, nights, days, startDate, whereToStay, plans);
    }
}
