package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    public void validateSplittingInputByDelimiter_specialSignDelimiterNoMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.validateSplittingInputByDelimiter(new CalculationData("1w2w3", "=")));
        assertEquals("'=' expected but 'w' found at position 1.", exception.getMessage());
    }

    @Test
    public void validateSplittingInputByDelimiter_specialSignDelimiterOneMatch_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.validateSplittingInputByDelimiter(new CalculationData("1|2,3", "|")));
        assertEquals("'|' expected but ',' found at position 3.", exception.getMessage());
    }

    @Test
    public void validateSplittingInputByDelimiter_specialSignDelimiterAllMatch_notThrowException() {
        assertDoesNotThrow(() -> InputValidationService.validateSplittingInputByDelimiter(new CalculationData("1|2|3", "|")));
    }

    @Test
    public void validateSplittingInputByDelimiter_specialSignDelimiterAllMatchWithNegativeNumber_notThrowException() {
        assertDoesNotThrow(() -> InputValidationService.validateSplittingInputByDelimiter(new CalculationData("1|-2|-3", "|")));
    }

    @Test
    public void validateSplittingInputByDelimiter_dashDelimiterAndNegativeNumber_notThrowException() {
        assertDoesNotThrow(() -> InputValidationService.validateSplittingInputByDelimiter(new CalculationData("1--2-3", "-")));
    }

    @Test
    public void validateSplittingInputByDelimiter_threeDashDelimiterAndNegativeNumber_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.validateSplittingInputByDelimiter(new CalculationData("1---2-3", "-")));
        assertEquals("'-' expected but multiple '-' found", exception.getMessage());
    }

    @Test
    public void verifyNegativeNumbers_inputContainsOneNegativeNumbers_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.verifyNegativeNumbers(Arrays.asList(1,-2,3)));
        assertEquals("Negative number(s) not allowed: -2", exception.getMessage());
    }

    @Test
    public void verifyNegativeNumbers_inputContainsMultipleNegativeNumbers_throwException() {
        var exception = assertThrows(IncorrectInputFormatException.class, () ->
                InputValidationService.verifyNegativeNumbers(Arrays.asList(1,-2,-3,-4)));
        assertEquals("Negative number(s) not allowed: -2, -3, -4", exception.getMessage());
    }

    @Test
    public void verifyNegativeNumbers_inputContainsOnlyPositiveNumbers_notThrowException() {
        assertDoesNotThrow(() -> InputValidationService.verifyNegativeNumbers(Arrays.asList(1,2,3)));
    }
}