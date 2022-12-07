package com.westssun.designpatterns._03_behavioral_patterns._18_memento._01_before;

/**
 * 메멘토 패턴
 * 캡슐화를 유지하면서 객체 내부 상태를 외부에 저장하는 방법
 */
public class Client {

    public static void main(String[] args) {
        Game game = new Game();
        game.setRedTeamScore(10);
        game.setBlueTeamScore(20);

        int blueTeamScore = game.getBlueTeamScore();
        int redTeamScore = game.getRedTeamScore();

        // 게임 재진행
        // 클라이언트가 게임의 내부 정보가 뭐가있는지를 무조건 알아야한다.
        // 이 결합력을 깨줘야한다.
        Game restoredGame = new Game();
        restoredGame.setBlueTeamScore(blueTeamScore);
        restoredGame.setRedTeamScore(redTeamScore);
    }
}
