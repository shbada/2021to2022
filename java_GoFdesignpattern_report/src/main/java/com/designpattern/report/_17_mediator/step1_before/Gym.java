package com.designpattern.report._17_mediator.step1_before;

public class Gym {
    private CleaningService cleaningService;

    public void clean() {
        cleaningService.clean(this);
    }
}
