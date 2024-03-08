package services;

import models.CalculationData;

public class CustomDelimiterService {

    private static final String DELIMITER_PREFIX = "//";
    private static final String DELIMITER_SUFFIX = "\n";

    public static CalculationData split(String input) {
        String delimiter = DelimiterService.DEFAULT_DELIMITERS;
        if(checkIfContainsCustomDelimiterDefinition(input)) {
            delimiter = getCustomDelimiter(input);
            input = getInputWithoutCustomDelimiterDefinition(input, delimiter);
        }

        return new CalculationData(input, delimiter);
    }

    public static boolean checkIfContainsCustomDelimiterDefinition(String input) {
        return input.startsWith(DELIMITER_PREFIX) && input.contains(DELIMITER_SUFFIX);
    }

    private static String getCustomDelimiter(String str) {
        String customDelimiter = extractCustomDelimiter(str);
        if(customDelimiter.isEmpty())
            customDelimiter = DelimiterService.DEFAULT_DELIMITERS;

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
