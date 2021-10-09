package item.aop;

import item.Html;
import item.IBrowser;

public class AopBrowser implements IBrowser {

    private String url;
    private Html html;

    /* Runnable */
    private Runnable before;
    private Runnable after;

    public AopBrowser(String url, Runnable before, Runnable after) {
        this.url = url;
        this.before = before;
        this.after = after;
    }

    @Override
    public Html show() {
        /* 이전 호출 */
        before.run();

        if (html == null) {
            html = new Html(url);
            System.out.println("aopBrowser html loading from : " + url);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* 이후 호출 */
        after.run();

        System.out.println("aopBrowser html cache aop : " + url);
        return html;
    }
}
