package hello.core.lifecycle;

public class NetworkClient_method {

    private String url;

    public NetworkClient_method() {
        //System.out.println("생성자 호출, url = " + url);
        //connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //
    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    /**
     * 빈 등록시, @Bean(initMethod = "init", destroyMethod = "close") 로 설정 추가
     * @throws Exception
     */
    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
