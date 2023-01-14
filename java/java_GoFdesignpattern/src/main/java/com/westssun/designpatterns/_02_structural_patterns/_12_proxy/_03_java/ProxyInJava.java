package com.westssun.designpatterns._02_structural_patterns._12_proxy._03_java;

import com.westssun.designpatterns._02_structural_patterns._12_proxy._02_after.DefaultGameService;
import com.westssun.designpatterns._02_structural_patterns._12_proxy._02_after.GameService;

import java.lang.reflect.Proxy;

/**
 * 런타임 프록시 - Dynamic Proxy
 */
public class ProxyInJava {

    public static void main(String[] args) {
        ProxyInJava proxyInJava = new ProxyInJava();
        proxyInJava.dynamicProxy();
    }

    /**
     * 런타임에 동적으로 생성
     */
    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        gameServiceProxy.startGame();
    }

    /**
     * 인터페이스가 없으면 CGLIB 사용
     * @param target
     * @return
     */
    private GameService getGameServiceProxy(GameService target) {
        // proxy Instance 생성
        return  (GameService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{GameService.class}, (proxy, method, args) -> {
                    System.out.println("O");
                    method.invoke(target, args);
                    System.out.println("ㅁ");
                    return null;
                });
    }
}
