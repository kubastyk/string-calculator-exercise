package services;

import exceptions.IncorrectInputFormatException;
import models.CalculatorData;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DelimiterService {

    public static final String DEFAULT_DELIMITERS = ",|\n";
    private static final String ESCAPE_SPECIAL_CHAR = "\\";

    public static List<Integer> getNumbers(CalculatorData calculatorData) throws IncorrectInputFormatException {
        InputValidationService.validateInputWithCustomDelimiter(calculatorData);

        String[] numbers = splitWithDelimiter(calculatorData);
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
    }

    private static String[] splitWithDelimiter(CalculatorData calculatorData) {
        String delimiter = calculatorData.delimiter();
        String delimiterForSplit = delimiter.length() == 1 && delimiter.matches("[^a-zA-Z0-9 ]")
                ? ESCAPE_SPECIAL_CHAR + delimiter : delimiter;
        return calculatorData.numbers().split(delimiterForSplit);
    }
}
