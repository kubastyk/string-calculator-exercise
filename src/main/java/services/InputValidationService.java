package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidationService {

    private static final String END_SEPARATOR_EXCEPTION_MESSAGE = "Input data cannot end with the separator";

    public static void verifyIfInputEndsWithDelimiter(CalculationData cd) throws IncorrectInputFormatException {
        if(cd.delimiter().equals(DelimiterService.DEFAULT_DELIMITERS)) {
            if(cd.numbers().endsWith(DelimiterService.COMMA_DELIMITER)
                    || cd.numbers().endsWith(DelimiterService.NEW_LINE_DELIMITER)) {
                throw new IncorrectInputFormatException(END_SEPARATOR_EXCEPTION_MESSAGE);
            }
        } else if(cd.numbers().endsWith(cd.delimiter())) {
            throw new IncorrectInputFormatException(END_SEPARATOR_EXCEPTION_MESSAGE);
        }
    }

    public static void validateSplittingInputByDelimiter(CalculationData calculationData)
            throws IncorrectInputFormatException {
        for (char chr : calculationData.numbers().toCharArray()) {
            if(!(Character.isDigit(chr) || chr == calculationData.delimiter().charAt(0) || chr == '-')) {
                throw new IncorrectInputFormatException("'%s' expected but '%s' found at position %s."
                        .formatted(calculationData.delimiter(), chr, calculationData.numbers().indexOf(chr)));
            }
        }

        if(calculationData.delimiter().equals("-")
                && Pattern.matches("\\S+[-]{3,}\\S+", calculationData.numbers())) {
            throw new IncorrectInputFormatException("'-' expected but multiple '-' found");
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
