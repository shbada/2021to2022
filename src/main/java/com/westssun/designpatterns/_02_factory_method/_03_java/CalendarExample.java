package com.westssun.designpatterns._02_factory_method._03_java;

import java.util.Calendar;
import java.util.Locale;

public class CalendarExample {

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getClass());

        // (오버로딩) Locale.forLanguageTag("th-TH-x-lvariant-TH") 파라미터를 넘기면?
        // 파라미터에 따라 인스턴스가 달라진다.
        System.out.println(Calendar.getInstance(Locale.forLanguageTag("th-TH-x-lvariant-TH")).getClass());
        System.out.println(Calendar.getInstance(Locale.forLanguageTag("ja-JP-x-lvariant-JP")).getClass());
    }
}
