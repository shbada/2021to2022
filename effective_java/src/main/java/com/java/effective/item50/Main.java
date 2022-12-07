package com.java.effective.item50;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();


        Period p = new Period(start, end);
        // end.setYear(72); // p 의 내부 수정
        // p.getEnd().setYear(78);
    }
}
