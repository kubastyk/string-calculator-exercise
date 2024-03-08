package services;

import exceptions.IncorrectInputFormatException;
import models.CalculatorData;

import java.util.List;
import java.util.stream.Collectors;

public class InputValidationService {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_DELIMITER = "\n";

    public static void verifyIfInputEndsWithDelimiter(String input) throws IncorrectInputFormatException {
        if(input.endsWith(COMMA_DELIMITER) || input.endsWith(NEW_LINE_DELIMITER))
            throw new IncorrectInputFormatException("Input data cannot end with the separator");
    }

    public static void validateInputWithCustomDelimiter(CalculatorData calculatorData) throws IncorrectInputFormatException {
        for (char chr : calculatorData.numbers().toCharArray()) {
            if(!(Character.isDigit(chr) || chr == calculatorData.delimiter().charAt(0))) {
                throw new IncorrectInputFormatException("'%s' expected but '%s' found at position %s."
                        .formatted(calculatorData.delimiter(), chr, calculatorData.numbers().indexOf(chr)));
            }
        }
    }

    public static void verifyNegativeNumbers(List<Integer> numbers) throws IncorrectInputFormatException {
        String negativeNumbers = numbers.stream()
                .filter(num -> num < 0)
                .map(num -> Integer.toString(num))
                .collect(Collectors.joining(", "));

        if(!negativeNumbers.isEmpty()) {
            throw new IncorrectInputFormatException("Negative number(s) not allowed: " + negativeNumbers);
        }
    }
}
