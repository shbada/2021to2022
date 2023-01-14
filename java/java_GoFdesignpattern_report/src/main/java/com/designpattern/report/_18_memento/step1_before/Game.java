package com.designpattern.report._18_memento.step1_before;

import java.io.Serializable;

public class Game implements Serializable {

    private int redTeamScore; // 레드팀

    private int blueTeamScore; // 블루팀

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
}
