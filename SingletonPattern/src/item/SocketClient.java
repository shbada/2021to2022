package item;

public class SocketClient {

    private static SocketClient socketClient = null;

    /* 기본 생성자 막음 */
    // public SocketClient() {}
    private SocketClient() {}

    /**
     * 싱글톤 패턴
     * @return
     */
    public static SocketClient getInstance() {
        /* 객체가 비어있을 경우만 객체 생성 */
        if (socketClient == null) {
            socketClient = new SocketClient();
        }

        return socketClient;
    }

    public void connect() {
        System.out.println("connect");
    }
}
