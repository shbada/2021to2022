package com.java.effective.item16;

public class PointFinal {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;

    public final int hour;
    public final int minus;

    /** 유효한 시간임을 보장 */
    public PointFinal(int hour, int minus) {
        if (hour < 0 || hour >= HOURS_PER_DAY) {
            throw new IllegalArgumentException("시간 : " + hour);
        }
        if (minus < 0 || minus >= MINUTES_PER_HOUR) {
            throw new IllegalArgumentException("분 : " + minus);
        }
        this.hour = hour;
        this.minus = minus;
    }
}
