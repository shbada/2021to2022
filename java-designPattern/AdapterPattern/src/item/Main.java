package item;

public class Main {
    public static void main(String[] args) {
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        /** Adaptor 만들기 : 220V 객체를 110V 로 만들어줘야한다. (SocketAdapter.java) */
        Cleaner cleaner = new Cleaner();
        // connect(cleaner);
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);
    }

    // 콘센트 (110V 사용가능)
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
