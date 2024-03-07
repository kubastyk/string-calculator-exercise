package services;

import exceptions.IncorrectInputFormatException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomDelimiterServiceTest {

    @Test
    public void split_properlyDefinedCustomDelimiter_returnListOfNumbers() throws IncorrectInputFormatException {
        var result = CustomDelimiterService.split("//aaa\n1aaa2aaa3");
        var result2 = CustomDelimiterService.split("//,\n1,2,3");
        var result3 = CustomDelimiterService.split("// \n1 2 3");
        var result4 = CustomDelimiterService.split("//9\n19293");

        List<Integer> expectedList = Arrays.asList(1, 2, 3);
        assertEquals(expectedList, result);
        assertEquals(expectedList, result2);
        assertEquals(expectedList, result3);
        assertEquals(expectedList, result4);
    }

    @Test
    public void split_properlyDefinedCustomDelimiterSpecialChar_returnListOfNumbers() throws IncorrectInputFormatException {
        var result = CustomDelimiterService.split("//|\n1|2|3");

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void split_specialSignDelimiterNoMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                CustomDelimiterService.split("//=\n1w2w3"));
        assertEquals("'=' expected but 'w' found at position 1.", exception.getMessage());
    }

    @Test
    public void split_specialSignDelimiterOneMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                CustomDelimiterService.split("//|\n1|2,3"));
        assertEquals("'|' expected but ',' found at position 3.", exception.getMessage());
    }

    @Test
    public void split_wrongDefinitionOfCustomDelimiterPrefix_throwException() {
        assertThrows(NumberFormatException.class, () ->
                CustomDelimiterService.split("/=\n1=2,3"));
    }

    @Test
    public void split_wrongDefinitionOfCustomDelimiterNoSuffix_throwException() {
        assertThrows(NumberFormatException.class, () ->
                CustomDelimiterService.split("//=1=2,3"));
    }

}