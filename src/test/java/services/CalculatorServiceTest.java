package services;

import exceptions.IncorrectInputFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private static CalculatorService calculatorService;

    @BeforeAll
    public static void beforeAll() {
        calculatorService = new CalculatorService();
    }

    @Test
    void add_emptyString_returnZero() throws IncorrectInputFormatException {
        int result = calculatorService.add("");
        assertEquals(0, result);
    }

    @Test
    public void add_oneNumberString_returnSameNumber() throws IncorrectInputFormatException {
        int result = calculatorService.add("1");
        assertEquals(1, result);
    }

    @Test
    public void add_multipleValidNumbersString_returnSum() throws IncorrectInputFormatException {
        int result = calculatorService.add("1,2");
        assertEquals(3, result);
    }

    @Test
    void add_multipleValidStringArguments_returnSumOfAllArguments() throws IncorrectInputFormatException {
        int result = calculatorService.add("1,2", "3,4", "5", "");
        assertEquals(15, result);
    }

    @Test
    public void add_properlyDefinedCustomDelimiter_returnListOfNumbers() throws IncorrectInputFormatException {
        int result = calculatorService.add("//aaa\n1aaa2aaa3");
        int result2 = calculatorService.add("//,\n1,2,3");
        int result3 = calculatorService.add("// \n1 2 3");
        int result4 = calculatorService.add("//9\n19293");

        assertEquals(6, result);
        assertEquals(6, result2);
        assertEquals(6, result3);
        assertEquals(6, result4);
    }

    @Test
    public void add_properlyDefinedCustomDelimiterSpecialChar_returnListOfNumbers() throws IncorrectInputFormatException {
        int result = calculatorService.add("//|\n1|2|3");

        assertEquals(6, result);
    }

    @Test
    public void add_testIfNumberBiggerThanLimitIsIgnored_sumWithoutNumberOverLimit() throws IncorrectInputFormatException {
        int result = calculatorService.add("1,2,3,1001");

        assertEquals(6, result);
    }

    @Test
    public void add_testEdgeCaseForMaxLimit_sumWithNumberOverLimit() throws IncorrectInputFormatException {
        int result = calculatorService.add("1000");

        assertEquals(1000, result);
    }

    @Test
    public void add_testMaxValueOneOverLimitValue_sumReturnZero() throws IncorrectInputFormatException {
        int result = calculatorService.add("1001");

        assertEquals(0, result);
    }
}