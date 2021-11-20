package com.java.effective.item50;

import java.util.Date;

public class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException();

        }
//        if (start.compareTo(end) > 0) {
//            throw new IllegalArgumentException();
//        }
//
//        this.start = start;
//        this.end = end;
    }

    public Date start() {
        return new Date(end.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }
}
