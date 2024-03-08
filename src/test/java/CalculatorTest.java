import exceptions.IncorrectInputFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void add_multipleValidStringArguments_returnSumOfAllArguments() {
        int result = calculator.add("1,2", "3,4", "5", "");
        assertEquals(15, result);
    }

    @Test
    public void add_properlyDefinedCustomDelimiter_returnListOfNumbers() {
        int result = calculator.add("//aaa\n1aaa2aaa3");
        int result2 = calculator.add("//,\n1,2,3");
        int result3 = calculator.add("// \n1 2 3");
        int result4 = calculator.add("//9\n19293");

        assertEquals(6, result);
        assertEquals(6, result2);
        assertEquals(6, result3);
        assertEquals(6, result4);
    }

    @Test
    public void add_properlyDefinedCustomDelimiterSpecialChar_returnListOfNumbers() {
        int result = calculator.add("//|\n1|2|3");

        assertEquals(6, result);
    }

}