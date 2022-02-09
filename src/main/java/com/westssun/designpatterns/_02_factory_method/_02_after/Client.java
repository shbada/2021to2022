package com.westssun.designpatterns._02_factory_method._02_after;

public class Client {

//    public static void main(String[] args) {
//        /**
//         * WhiteShipFactory 가 생길수록 client 코드는 수정이 발생할 수 있음.
//         * Ship, ShipFactory 부분에 집중하자
//         */
//        Ship whiteship = new WhiteShipFactory().orderShip("Whiteship", "keesun@mail.com");
//        System.out.println(whiteship);
//
//        Ship blackship = new BlackShipFactory().orderShip("Blackship", "keesun@mail.com");
//        System.out.println(blackship);
//    }

    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteShipFactory(), "whiteship", "keesun@mail.com");
        client.print(new BlackShipFactory(), "blackship", "keesun@mail.com");
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }

}
