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
    public void add_withLetterAsValue_throwNumberFormatException() {
        assertThrows(NumberFormatException.class, () ->
                calculator.add("1,A"));
    }

    @Test
    public void add_withNotProperDelimiter_throwNumberFormatException() {
        assertThrows(NumberFormatException.class, () ->
                calculator.add("1.2"));
    }

    @Test
    public void add_withNoNumbersProvided_throwNumberFormatException() {
        assertThrows(NumberFormatException.class, () ->
                calculator.add("AA,BB"));
    }

    @Test
    void add_multipleValidStringArguments_returnSumOfAllArguments() {
        int result = calculator.add("1,2", "3,4", "5", "");
        assertEquals(15, result);
    }

    @Test
    public void add_multipleArgumentsWrongDelimiter_returnSumOfAllArguments() {
        assertThrows(NumberFormatException.class, () ->
                calculator.add("1,2", "3.4"));
    }

    @Test
    public void add_multipleValidNumbersWithNewLineSeparator_returnSum() {
        int result = calculator.add("1\n2\n3");
        assertEquals(6, result);
    }

    @Test
    public void add_multipleValidNumbersWithNewLineAndCommaSeparator_returnSum() {
        int result = calculator.add("1,2\n3");
        assertEquals(6, result);
    }
}