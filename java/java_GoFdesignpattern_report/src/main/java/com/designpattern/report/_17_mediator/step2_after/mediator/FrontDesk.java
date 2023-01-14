package com.designpattern.report._17_mediator.step2_after.mediator;

import com.designpattern.report._17_mediator.step2_after.CleaningService;
import com.designpattern.report._17_mediator.step2_after.Guest;
import com.designpattern.report._17_mediator.step2_after.Restaurant;

import java.time.LocalDateTime;

/**
 * Mediator
 */

/**
 * 여러 객체들이 소통하는 방법을 캡슐화하는 패턴
 *
 * 여러 컴포넌트간의 결합도를 중재자를 통해 낮출 수 있다.
 *
 * Mediator(Interface) - ConcreteMediator
 * Colleague -> Mediator
 *
 * ColleagueA, ColleagueB
 *
 * ConcreteMediator -> ColleagueA, ColleagueB
 *
 *
 *
 */
public class FrontDesk {

    private CleaningService cleaningService = new CleaningService();

    private Restaurant restaurant = new Restaurant();

    public void getTowers(Guest guest, int numberOfTowers) {
        cleaningService.getTowers(guest.getId(), numberOfTowers);
    }

    public String getRoomNumberFor(Integer guestId) {
        return "1111";
    }

    public void dinner(Guest guest, LocalDateTime dateTime) {
        restaurant.dinner(guest.getId(), dateTime);
    }
}
