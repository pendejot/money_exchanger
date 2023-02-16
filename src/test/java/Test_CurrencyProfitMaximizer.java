import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Test_CurrencyProfitMaximizer {

    private CurrencyProfitMaximizer currencyProfitMaximizer;

    @Before
    public void setup() {
        currencyProfitMaximizer = new CurrencyProfitMaximizer();
    }

    @Test
    public void testCalculateGain_EurHuf() {
        double currentPrice = 363.5;
        double averagePrice = 335.2;

        double expectedGain = 8.43;
        double actualGain = currencyProfitMaximizer.calculateGain(currentPrice, averagePrice);

        Assert.assertEquals(expectedGain, actualGain, 0.03);
    }

    @Test
    public void testCalculateGain_UsdHuf() {
        double currentPrice = 295.67;
        double averagePrice = 281.15;

        double expectedGain = 5.17;
        double actualGain = currencyProfitMaximizer.calculateGain(currentPrice, averagePrice);

        Assert.assertEquals(expectedGain, actualGain, 0.03);
    }

    @Test
    public void testCalculateGain_GbpHuf() {
        double currentPrice = 409.62;
        double averagePrice = 373.37;

        double expectedGain = 9.68;
        double actualGain = currencyProfitMaximizer.calculateGain(currentPrice, averagePrice);

        Assert.assertEquals(expectedGain, actualGain, 0.03);
    }

}
