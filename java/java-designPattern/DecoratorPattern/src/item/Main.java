package item;

public class Main {
    public static void main(String[] args) {
        ICar audi = new Audi(1000);
        audi.showPrice();

        /* 차량 등급 a3, a4, a5... */
        // a3
        ICar audi3 = new A3(audi, "a3");
        audi3.showPrice();

        // a4
        ICar audi4 = new A4(audi, "a4");
        audi4.showPrice();

        // a5
        ICar audi5 = new A5(audi, "a5");
        audi5.showPrice();
    }
}
