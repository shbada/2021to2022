package com.westssun.designpatterns._04_builder._01_before;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        // 생성자를 오버로드 하여 생성자가 여러개일때 어떤걸 써야할지 헷갈린다.
        // TourPlan anothorPlan = new TourPlan();

        /* 짧은 여행 만들기 */
        TourPlan shortTrip = new TourPlan();
        shortTrip.setTitle("오레곤 롱비치 여행");
        shortTrip.setStartDate(LocalDate.of(2021, 7, 15));
        // setDays 가 셋팅되면 setNights 도 셋팅되도록 강제화하고싶을때 할수가 없다.
//        shortTrip.setDays(1);
//        shortTrip.setNights(1);

        /* TourPlan 만들기 */
        TourPlan tourPlan = new TourPlan();
        tourPlan.setTitle("칸쿤 여행");
        tourPlan.setNights(2);
        tourPlan.setDays(3);
        tourPlan.setStartDate(LocalDate.of(2020, 12, 9));
        tourPlan.setWhereToStay("리조트");
        tourPlan.addPlan(0, "체크인 이후 짐풀기");
        tourPlan.addPlan(0, "저녁 식사");
        tourPlan.addPlan(1, "조식 부페에서 식사");
        tourPlan.addPlan(1, "해변가 산책");
        tourPlan.addPlan(1, "점심은 수영장 근처 음식점에서 먹기");
        tourPlan.addPlan(1, "리조트 수영장에서 놀기");
        tourPlan.addPlan(1, "저녁은 BBQ 식당에서 스테이크");
        tourPlan.addPlan(2, "조식 부페에서 식사");
        tourPlan.addPlan(2, "체크아웃");
    }
}
