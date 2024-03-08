package services;

import exceptions.IncorrectInputFormatException;
import models.CalculatorData;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CustomDelimiterService {

    public static final String DEFAULT_DELIMITERS = ",|\n";
    private static final String DELIMITER_PREFIX = "//";
    private static final String DELIMITER_SUFFIX = "\n";
    private static final String ESCAPE_SPECIAL_CHAR = "\\";

    public static CalculatorData split(String input) {
        String delimiter = DEFAULT_DELIMITERS;
        if(checkIfContainsCustomDelimiterDefinition(input)) {
            delimiter = getCustomDelimiter(input);
            input = getInputWithoutCustomDelimiterDefinition(input, delimiter);
        }

        return new CalculatorData(input, delimiter);
    }

    public static List<Integer> getNumbers(CalculatorData calculatorData) throws IncorrectInputFormatException {
        InputValidationService.validateInputWithCustomDelimiter(calculatorData);

        String[] numbers = splitWithDelimiter(calculatorData);
        return Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(toList());
    }

    public static boolean checkIfContainsCustomDelimiterDefinition(String input) {
        return input.startsWith(DELIMITER_PREFIX) && input.contains(DELIMITER_SUFFIX);
    }

    private static String[] splitWithDelimiter(CalculatorData calculatorData) {
        String delimiter = calculatorData.delimiter();
        String delimiterForSplit = delimiter.length() == 1 && delimiter.matches("[^a-zA-Z0-9 ]")
                ? ESCAPE_SPECIAL_CHAR + delimiter : delimiter;
        return calculatorData.numbers().split(delimiterForSplit);
    }

    private static String getCustomDelimiter(String str) {
        String customDelimiter = extractCustomDelimiter(str);
        if(customDelimiter.isEmpty())
            customDelimiter = DEFAULT_DELIMITERS;

        return customDelimiter;
    }

    private static String getInputWithoutCustomDelimiterDefinition(String str, String delimiter) {
        return removeDefinitionOfCustomDelimiter(str, delimiter);
    }

    private static String extractCustomDelimiter(String str) {
        return str.substring(DELIMITER_PREFIX.length(), str.indexOf(DELIMITER_SUFFIX));
    }

    private static String removeDefinitionOfCustomDelimiter(String input, String delimiter) {
        return input.substring(DELIMITER_PREFIX.length() + delimiter.length() + DELIMITER_SUFFIX.length());
    }
}
