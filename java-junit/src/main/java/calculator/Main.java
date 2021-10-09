package calculator;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello World");

        /* 기존의 테스트 방식 */
        Calculator calculator = new Calculator(new KrwCalculator());
        System.out.println(calculator.sum(10, 10));
        System.out.println(calculator.minus(10, 10));

        /* MarketApi */
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);

        dollarCalculator.init();

        Calculator marketCalculator = new Calculator(dollarCalculator);
        System.out.println(marketCalculator.sum(10, 10));
        System.out.println(marketCalculator.minus(10, 10));

        /** DollarCalculatorTest 를 생성해서 테스트를 해보자. 위 코드처럼 Main 에서 찍어보는게 아니다! */
    }
}
