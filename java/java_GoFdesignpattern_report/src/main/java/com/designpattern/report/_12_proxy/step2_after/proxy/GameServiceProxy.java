package com.designpattern.report._12_proxy.step2_after.proxy;

import com.designpattern.report._12_proxy.step2_after.subject.DefaultGameService;
import com.designpattern.report._12_proxy.step2_after.subject.GameService;

public class GameServiceProxy implements GameService {

    private GameService gameService;

//    public GameServiceProxy(GameService gameService) {
//        this.gameService = gameService;
//    }

    @Override
    public void startGame() {
        long before = System.currentTimeMillis();

        if (this.gameService == null) {
            this.gameService = new DefaultGameService();
        }

        // 실제 로직 수행
        gameService.startGame();

        System.out.println(System.currentTimeMillis() - before);
    }
}
