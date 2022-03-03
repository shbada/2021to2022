package com.designpattern.report._18_memento.step2_after.memento;

/**
 * 메멘토 역할
 *
 */
public final class GameSave { // 상속 방지
    // 값 변경 불가능
    private final int blueTeamScore;

    private final int redTeamScore;

    public GameSave(int blueTeamScore, int redTeamScore) {
        this.blueTeamScore = blueTeamScore;
        this.redTeamScore = redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public int getRedTeamScore() {
        return redTeamScore;
    }
}
