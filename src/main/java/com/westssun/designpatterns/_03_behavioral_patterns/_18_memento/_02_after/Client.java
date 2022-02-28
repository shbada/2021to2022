package com.westssun.designpatterns._03_behavioral_patterns._18_memento._02_after;

public class Client {

/**
 * 메멘토 패턴
 * 캡슐화를 유지하면서 객체 내부 상태를 외부에 저장하는 방법
 * Originator
 * Memento
 *
 * 객체 상태를 외부에 저장했다가 해당 상태로 다시 복구할 수 있다.
 *
 * [장점]
 * 스냅샷 코드로 유연하게 처리 가능
 * 클라이언트의 코드 변경을 줄임
 * 어떤 origin 게임 상태를 저장해놓고 원하는 시점으로 복원이 가능한 책임을 질 careTaker 사용 가능 (현재는 client)
 *
 * [단점]
 * 메멘토 객체가 많은 정보를 담고있고, 이 메멘토 객체를 자주 생성한다면 메모리에 영향
 * 오래된 메멘토를 정리해주는 별도의 역할(careTaker에 추가) 이 필요할 수도 있다.
*/
public static void main(String[] args) {
        Game game = new Game();
        game.setBlueTeamScore(10);
        game.setRedTeamScore(20);

        // 신규 등록 (스냅샷)
        GameSave save = game.save();

        game.setBlueTeamScore(12);
        game.setRedTeamScore(22);

        // 복구 (save 로 복원하다)
        game.restore(save);

        System.out.println(game.getBlueTeamScore());
        System.out.println(game.getRedTeamScore());
    }
}
