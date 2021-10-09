package item_cache;

import item_cache.cache.BrowserProxy;

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
    }
}
