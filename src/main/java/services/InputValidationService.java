package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;

import java.util.List;
import java.util.stream.Collectors;

public class InputValidationService {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_DELIMITER = "\n";

    public static void verifyIfInputEndsWithDelimiter(String input) throws IncorrectInputFormatException {
        if(input.endsWith(COMMA_DELIMITER) || input.endsWith(NEW_LINE_DELIMITER))
            throw new IncorrectInputFormatException("Input data cannot end with the separator");
    }

    public static void validateSplittingInputByDelimiter(CalculationData calculationData) throws IncorrectInputFormatException {
        for (char chr : calculationData.numbers().toCharArray()) {
            if(!(Character.isDigit(chr) || chr == calculationData.delimiter().charAt(0))) {
                throw new IncorrectInputFormatException("'%s' expected but '%s' found at position %s."
                        .formatted(calculationData.delimiter(), chr, calculationData.numbers().indexOf(chr)));
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
