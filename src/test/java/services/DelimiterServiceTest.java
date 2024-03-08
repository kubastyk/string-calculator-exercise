package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DelimiterServiceTest {

    @Test
    public void getNumbers_properlyDefinedCustomDelimiter_returnListOfNumbers() throws IncorrectInputFormatException {
        var result = DelimiterService.extractNumbers(new CalculationData("1aaa2aaa3", "aaa"));
        var result2 = DelimiterService.extractNumbers(new CalculationData("1,2,3", ","));
        var result3 = DelimiterService.extractNumbers(new CalculationData("1 2 3", " "));
        var result4 = DelimiterService.extractNumbers(new CalculationData("19293", "9"));

        List<Integer> expectedList = Arrays.asList(1, 2, 3);
        assertEquals(expectedList, result);
        assertEquals(expectedList, result2);
        assertEquals(expectedList, result3);
        assertEquals(expectedList, result4);
    }

    @Test
    public void getNumbers_properlyDefinedCustomDelimiterSpecialChar_returnListOfNumbers() throws IncorrectInputFormatException {
        var result = DelimiterService.extractNumbers(new CalculationData("1|2|3", "|"));

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void getNumbers_specialSignDelimiterNoMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                DelimiterService.extractNumbers(new CalculationData("1w2w3", "=")));
        assertEquals("'=' expected but 'w' found at position 1.", exception.getMessage());
    }

    @Test
    public void getNumbers_specialSignDelimiterOneMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                DelimiterService.extractNumbers(new CalculationData("1|2,3", "|")));
        assertEquals("'|' expected but ',' found at position 3.", exception.getMessage());
    }

}