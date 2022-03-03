package com.designpattern.report._08_composite.step1_before;

public class Client {

    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        /*
           '전체', '부분' 모두 동일한 컴포넌트로 인식할 수 있는 계층 구조를 만든다.
           그룹 전체와 개별 객체를 동일하게 처리할 수 있다. -> 컴포짓 패턴 (Composite)
         */
        Client client = new Client();
        // 부분
        client.printPrice(doranBlade);
        // 전체
        client.printPrice(bag);
    }

    private void printPrice(Item item) {
        System.out.println(item.getPrice());
    }

    private void printPrice(Bag bag) {
        int sum = bag.getItems().stream().mapToInt(Item::getPrice).sum();
        System.out.println(sum);
    }

}
