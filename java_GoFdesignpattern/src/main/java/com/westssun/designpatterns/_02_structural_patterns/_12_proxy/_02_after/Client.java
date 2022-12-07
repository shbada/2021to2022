package com.westssun.designpatterns._02_structural_patterns._12_proxy._02_after;

/**
 * 프록시 패턴
 * - 특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있는 패턴
 *
 * Subject (interface)
 * RealSubject
 *
 * Proxy
 *
 * Proxy -> RealSubject
 * Client -> Subject
 *
 * [장점]
 * - 기존 코드를 변경하지 않고 새로운 기능을 추가할 수 있다.
 * - 기존 코드가 해야할일만 유지할 수 있다.
 * - 기능 추가 및 초기화 지연 등으로 다양하게 활용할 수 있다.
 *
 * [단점]
 * - 코드가 복잡해진다.
 */
public class Client {

    public static void main(String[] args) {
        // GameService > GameServiceProxy
        // GameService gameService = new GameServiceProxy(new DefaultGameService());
        GameService gameService = new GameServiceProxy();
        gameService.startGame();
    }
}
