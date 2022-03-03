package com.designpattern.report._17_mediator.step2_after;

import com.designpattern.report._17_mediator.step2_after.mediator.FrontDesk;

public class CleaningService {

    private FrontDesk frontDesk = new FrontDesk();

    public void getTowers(Integer guestId, int numberOfTowers) {
        String roomNumber = this.frontDesk.getRoomNumberFor(guestId);
        System.out.println("provide " + numberOfTowers + " to " + roomNumber);
    }
}
