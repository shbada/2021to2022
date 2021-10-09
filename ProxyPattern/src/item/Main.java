package item;

import item.aop.AopBrowser;
import item.cache.BrowserProxy;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
//        Browser browser = new Browser("www.naver.com");
//        /* 여러번 호출 */
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();

        /* 캐시 구현해보자. */
        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();

        /* AOP 구현해보자. */
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                () -> {
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                () -> {
                    long now = System.currentTimeMillis();
                    System.out.println("end");
                    end.set(now - start.get());
                });

        aopBrowser.show();
        System.out.println("loading time: " + end.get()); // 1.5초 정도

        aopBrowser.show();
        System.out.println("loading time: " + end.get()); // 캐시이므로 0
    }
}
