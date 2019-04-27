import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ChocolatesTest {

    @Test
    public void testFileInput() throws IOException {
        Chocolates.parseOrdersFile("input/orders.csv");
    }

    @Test
    public void testSampleInputFile() {
        String finalMilkOrder = Chocolates.tallyFinalOrder("milk", 12, 2, 5);
        String finalDarkOrder = Chocolates.tallyFinalOrder("dark", 13, 4, 1);
        String finalWhiteOrder = Chocolates.tallyFinalOrder("white", 6, 2, 2);

        assertEquals("milk 7,dark 0,white 0", finalMilkOrder);
        assertEquals("milk 0,dark 9,white 0", finalDarkOrder);
        assertEquals("milk 1,dark 0,white 4", finalWhiteOrder);
    }

    @Test
    public void testUnderpayment() {
        String finalOrder = Chocolates.tallyFinalOrder("milk", 1, 5, 2);
        assertEquals("milk 0,dark 0,white 0", finalOrder);
    }

    @Test
    public void testZeroCash() {
        String finalOrder = Chocolates.tallyFinalOrder("dark", 0, 7, 4);
        assertEquals("milk 0,dark 0,white 0", finalOrder);
    }

    @Test
    public void testZeroBonusRatio() {
        String finalOrder = Chocolates.tallyFinalOrder("milk", 5, 2, 0);
        assertEquals("milk 2,dark 0,white 0", finalOrder);
    }

    @Test
    public void testZeroPrice() {
        String finalOrder = Chocolates.tallyFinalOrder("milk", 5, 0, 6);
        assertEquals("milk 5,dark 0,white 0", finalOrder);
    }

    @Test
    public void testLargeNumbers() {
        String finalOrder = Chocolates.tallyFinalOrder("white", 2147483647, 2, 1);
        assertEquals("milk 1073741823,dark 0,white 2147483646", finalOrder);
    }

    @Test(expected = IllegalStateException.class)
    public void testUpperCase() {
        Chocolates.tallyFinalOrder("MILK", 22, 5, 3);
    }

    @Test(expected = IllegalStateException.class)
    public void testWrongChocolateType() {
        Chocolates.tallyFinalOrder("ruby", 10, 3, 2);
    }

    @Test(expected = IllegalStateException.class)
    public void testExtraQuotes() {
        Chocolates.tallyFinalOrder("\"milk\"", 12, 2, 5);
    }
}
