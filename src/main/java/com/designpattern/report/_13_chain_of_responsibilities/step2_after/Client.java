package com.designpattern.report._13_chain_of_responsibilities.step2_after;

import com.designpattern.report._13_chain_of_responsibilities.step2_after.concrete.AuthRequestHandler;
import com.designpattern.report._13_chain_of_responsibilities.step2_after.concrete.LoggingRequestHandler;
import com.designpattern.report._13_chain_of_responsibilities.step2_after.concrete.PrintRequestHandler;

/**
 * 책임 연쇄 패턴
 * [장점]
 * - 단일책임원칙
 * [단점]
 * - 디버깅이 어렵다.
 */
public class Client {

    private RequestHandler requestHandler;

    public Client(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void doWork() {
        Request request = new Request("이번 놀이는 뽑기입니다.");
        requestHandler.handle(request);
    }

    public static void main(String[] args) {
        // 책임 연쇄 적용
        // 특정한 핸들러만 수행시키게 할수도 있고 등등 핸들러의 설계를 다르게 할수도 있다
        // 중요한건 클라이언트가 본인이 어떤 핸들러를 써야할지 결정하지 않는다
        // 요청을 처리하는 쪽과 결합이 끊겨져있다. (중요,장점)
        // 클라이언트가 구체적인 어떤 클래스의 존재/사용 여부를 몰라도 된다.
        RequestHandler chain = new AuthRequestHandler(new LoggingRequestHandler(new PrintRequestHandler(null)));
        Client client = new Client(chain);
        client.doWork();
    }
}
