package services;

import exceptions.IncorrectInputFormatException;
import models.CalculatorData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationServiceTest {

    @Test
    public void verifyIfInputEndsWithDelimiter_notAllowCommaSeparatorAtTheEndOfString_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.verifyIfInputEndsWithDelimiter("1,2,"));
        assertEquals("Input data cannot end with the separator", exception.getMessage());
    }

    @Test
    public void verifyIfInputEndsWithDelimiter_notAllowNewLineSeparatorAtTheEndOfString_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.verifyIfInputEndsWithDelimiter("1\n2\n"));
        assertEquals("Input data cannot end with the separator", exception.getMessage());
    }

    @Test
    public void validateInputWithCustomDelimiter_specialSignDelimiterNoMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.validateInputWithCustomDelimiter(new CalculatorData("1w2w3", "=")));
        assertEquals("'=' expected but 'w' found at position 1.", exception.getMessage());
    }

    @Test
    public void validateInputWithCustomDelimiter_specialSignDelimiterOneMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.validateInputWithCustomDelimiter(new CalculatorData("1|2,3", "|")));
        assertEquals("'|' expected but ',' found at position 3.", exception.getMessage());
    }

    @Test
    public void validateInputWithCustomDelimiter_specialSignDelimiterAllMatch_notThrowException() {
        assertDoesNotThrow(() -> InputValidationService.validateInputWithCustomDelimiter(new CalculatorData("1|2|3", "|")));
    }
}