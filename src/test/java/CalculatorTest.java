import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    public static void beforeAll() {
        calculator = new Calculator();
    }

    @Test
    void add_emptyString_returnZero() {
        int result = calculator.add("");
        assertEquals(0, result);
    }

    @Test
    public void add_oneNumberString_returnSameNumber() {
        int result = calculator.add("1");
        assertEquals(1, result);
    }

    @Test
    public void add_multipleValidNumbersString_returnSum() {
        int result = calculator.add("1,2");
        assertEquals(3, result);
    }
}