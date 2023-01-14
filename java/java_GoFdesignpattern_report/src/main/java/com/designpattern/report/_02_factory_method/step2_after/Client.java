package com.designpattern.report._02_factory_method.step2_after;

import com.designpattern.report._02_factory_method.step2_after.creator.BlackshipFactory;
import com.designpattern.report._02_factory_method.step2_after.creator.ShipFactory;
import com.designpattern.report._02_factory_method.step2_after.creator.WhiteshipFactory;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteshipFactory(), "whiteship", "keesun@mail.com");
        client.print(new BlackshipFactory(), "blackship", "keesun@mail.com");
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }
}
