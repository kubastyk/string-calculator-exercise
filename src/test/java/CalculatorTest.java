import exceptions.IncorrectInputFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void add_properlyDefinedCustomDelimiter_returnListOfNumbers() {
        int result = calculator.add("//aaa\n1aaa2aaa3");
        int result2 = calculator.add("//.\n1.2.3");
        int result3 = calculator.add("// \n1 2 3");
        int result4 = calculator.add("//9\n19293");


        assertEquals(6, result);
        assertEquals(6, result2);
        assertEquals(6, result3);
        assertEquals(6, result4);
    }

    @Test
    public void add_specialSignDelimiterNoMatch_throwException() {
        assertThrows(NumberFormatException.class, () ->
                calculator.add("//=\n1w2w3"));
    }

    @Test
    public void add_specialSignDelimiterOneMatch_throwException() {
        assertThrows(NumberFormatException.class, () ->
                calculator.add("//=\n1=2,3"));
    }

    @Test
    public void add_wrongDefinitionOfCustomDelimiterPrefix_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                calculator.add("/=\n1=2,3"));
        assertEquals("Custom delimiter not properly defined", exception.getMessage());
    }

    @Test
    public void add_wrongDefinitionOfCustomDelimiterNoSuffix_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                calculator.add("//=1=2,3"));
        assertEquals("Custom delimiter not properly defined", exception.getMessage());
    }

}