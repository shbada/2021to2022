package item;

public class BClazz {
    private SocketClient socketClient;

    public BClazz() {
        // 이렇게 객체 생성이 안된다. (private 생성자)
        // this.socketClient = new SocketClient();
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}
