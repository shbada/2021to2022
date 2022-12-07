import item.AClazz;
import item.BClazz;
import item.SocketClient;

public class Main {
    public static void main(String[] args) {
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        /* 객체 동일 판단 : equals */
        System.out.println(aClient.equals(bClient)); // true
    }
}
