package item_cache.cache;

import item_cache.Html;
import item_cache.IBrowser;

public class BrowserProxy implements IBrowser {
    private String url;
    private Html html;

    public BrowserProxy(String url) {
        this.url = url;
    }

    /**
     * 캐싱 처리
     * @return
     */
    @Override
    public Html show() {
        if (html == null) {
            this.html = new Html(url);
            System.out.println("BrowserProxy loading html from: " + url);
        }

        System.out.println("BrowserProxy user cache html: " + url);
        return html;
    }
}
