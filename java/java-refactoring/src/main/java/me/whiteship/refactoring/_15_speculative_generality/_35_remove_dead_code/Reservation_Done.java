package me.whiteship.refactoring._15_speculative_generality._35_remove_dead_code;

import java.time.LocalDateTime;

/**
 * 테스트 코드에서만 참조하고 있는 코드
 * 삭제할 코드가 되겠구나. 라고 생각하고 삭제
 */
public class Reservation_Done {

    private String title;

    private LocalDateTime from;

    private LocalDateTime to;

    private LocalDateTime alarm;

    public Reservation_Done(String title, LocalDateTime from, LocalDateTime to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public void setAlarmBefore(int minutes) {
        this.alarm = this.from.minusMinutes(minutes);
    }

    public LocalDateTime getAlarm() {
        return alarm;
    }
}
