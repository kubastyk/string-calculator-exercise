package services;

import models.CalculationData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomDelimiterServiceTest {

    @Test
    public void split_properlyDefinedCustomDelimiter_returnListOfNumbers() {
        var result = CustomDelimiterService.split("//aaa\n1aaa2aaa3");
        var result2 = CustomDelimiterService.split("//,\n1,2,3");
        var result3 = CustomDelimiterService.split("// \n1 2 3");
        var result4 = CustomDelimiterService.split("//9\n19293");

        assertEquals(new CalculationData("1aaa2aaa3", "aaa"), result);
        assertEquals(new CalculationData("1,2,3", ","), result2);
        assertEquals(new CalculationData("1 2 3", " "), result3);
        assertEquals(new CalculationData("19293", "9"), result4);
    }

    @Test
    public void split_properlyDefinedCustomDelimiterSpecialChar_returnListOfNumbers() {
        var result = CustomDelimiterService.split("//|\n1|2|3");

        assertEquals(new CalculationData("1|2|3", "|"), result);
    }

    @Test
    public void split_wrongDefinitionOfCustomDelimiterPrefix_useDefaultValues() {
        var result = CustomDelimiterService.split("/=\\n1=2,3\"");

        assertEquals(new CalculationData("/=\\n1=2,3\"", DelimiterService.DEFAULT_DELIMITERS), result);
    }

    @Test
    public void split_wrongDefinitionOfCustomDelimiterNoSuffix_useDefaultValues() {
        var result = CustomDelimiterService.split("//=1=2,3");

        assertEquals(new CalculationData("//=1=2,3", DelimiterService.DEFAULT_DELIMITERS), result);
    }
}