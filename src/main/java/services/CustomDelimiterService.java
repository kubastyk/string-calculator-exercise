package services;

import exceptions.IncorrectInputFormatException;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CustomDelimiterService {

    private static final String DEFAULT_DELIMITERS = ",|\n";
    private static final String DELIMITER_PREFIX = "//";
    private static final String DELIMITER_SUFFIX = "\n";
    private static final String ESCAPE_SPECIAL_CHAR = "\\";

    public static List<Integer> split(String inut) throws IncorrectInputFormatException {
        String delimiter = DEFAULT_DELIMITERS;
        if(inut.startsWith(DELIMITER_PREFIX) && inut.contains(DELIMITER_SUFFIX)) {
            delimiter = getCustomDelimiter(inut);
            inut = getInputWithoutCustomDelimiterDefinition(inut, delimiter);

            InputValidationService.validateInputWithCustomDelimiter(inut, delimiter);
        }

        String[] numbers = splitWithDelimiter(inut, delimiter);
        return Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(toList());
    }

    private static String[] splitWithDelimiter(String input, String delimiter) {
        String delimiterForSplit = delimiter.length() == 1 && delimiter.matches("[^a-zA-Z0-9 ]")
                ? ESCAPE_SPECIAL_CHAR + delimiter : delimiter;
        return input.split(delimiterForSplit);
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
