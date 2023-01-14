package com.westssun.designpatterns._02_structural_patterns._12_proxy._01_before;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        GameService gameService = new GameService();
        // start
        gameService.startGame();
        // end

        // client 에서 수행시간을 계산하는 로직 넣지말고 프록시패턴을 사용해보자.
    }
}
