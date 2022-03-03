package com.designpattern.report._18_memento.step2_after.originator;

import com.designpattern.report._18_memento.step2_after.memento.GameSave;

/**
 * Originator
 */
public class Game {
    /* state */
    private int redTeamScore;

    private int blueTeamScore;

    public int getRedTeamScore() {
        return redTeamScore;
    }

    public void setRedTeamScore(int redTeamScore) {
        this.redTeamScore = redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public void setBlueTeamScore(int blueTeamScore) {
        this.blueTeamScore = blueTeamScore;
    }

    /**
     * 저장
     * @return
     */
    public GameSave save() {
        return new GameSave(this.blueTeamScore, this.redTeamScore);
    }

    /**
     * 복원 (Memento : gateSave)
     * @param gameSave
     */
    public void restore(GameSave gameSave) {
        this.blueTeamScore = gameSave.getBlueTeamScore();
        this.redTeamScore = gameSave.getRedTeamScore();
    }

}
