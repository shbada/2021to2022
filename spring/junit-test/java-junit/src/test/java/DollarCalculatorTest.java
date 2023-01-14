import calculator.Calculator;
import calculator.DollarCalculator;
import calculator.KrwCalculator;
import calculator.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    /* 테스트가 실행되기 전에 */
    @BeforeEach
    public void init() {
        /* marketApi 의 connect() 가 호출될 경우 return (1100) 을 3000을 리턴시키겠다는 의미 */
        Mockito.lenient().when(marketApi.connect())
                .thenReturn(3000);
    }

    @Test
    public void testHello() {
        System.out.println("hello");
    }

    @Test
    public void dollarTest() {
        /* Calculator */
        Calculator calculator = new Calculator(new KrwCalculator());

        Assertions.assertEquals(20, calculator.sum(10, 10));

        /* MarketApi */
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);

        dollarCalculator.init();

        Calculator marketCalculator = new Calculator(dollarCalculator);
        Assertions.assertEquals(22000, marketCalculator.sum(10, 10));
        Assertions.assertEquals(0, marketCalculator.minus(10, 10));

    }

    @Test
    public void dollarMockTest() {
        /* Calculator */
        Calculator calculator = new Calculator(new KrwCalculator());

        Assertions.assertEquals(20, calculator.sum(10, 10));

        /* MarketApi */
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi); // Mock

        dollarCalculator.init();

        Calculator marketCalculator = new Calculator(dollarCalculator);
        Assertions.assertEquals(60000, marketCalculator.sum(10, 10));
        Assertions.assertEquals(0, marketCalculator.minus(10, 10));

        System.out.println(marketCalculator.sum(10, 10));

    }
}
