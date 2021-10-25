package item2;

class CoffeeTest {
    public static void main(String[] args) {
        Coffee etiopiaCoffee = new EtiopiaAmericano();
        etiopiaCoffee.brewing();

        Latte latte = new Latte(etiopiaCoffee);
        latte.brewing();

        Mocha mocha = new Mocha(new Latte(new EtiopiaAmericano()));
        mocha.brewing();

        Coffee keyaCoffee = new KeyaAmericano();
        WhippingCream whippingCream = new WhippingCream(keyaCoffee);
        whippingCream.brewing();

        Coffee keyaCoffee2 = new WhippingCream(new Mocha(new Latte(new KeyaAmericano())));
        keyaCoffee2.brewing();
    }
}