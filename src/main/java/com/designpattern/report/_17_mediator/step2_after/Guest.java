package com.designpattern.report._17_mediator.step2_after;

import com.designpattern.report._17_mediator.step2_after.mediator.FrontDesk;

import java.time.LocalDateTime;

public class Guest {

    private Integer id;

    private FrontDesk frontDesk = new FrontDesk();

    public void getTowers(int numberOfTowers) {
        this.frontDesk.getTowers(this, numberOfTowers);
    }

    private void dinner(LocalDateTime dateTime) {
        this.frontDesk.dinner(this, dateTime);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
