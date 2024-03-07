package services;

import exceptions.IncorrectInputFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationServiceTest {

    @Test
    void verifyIfInputEndsWithDelimiter() {
    }

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
}