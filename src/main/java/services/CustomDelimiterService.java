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

    public static List<Integer> split(String text) throws IncorrectInputFormatException {
        String[] numbers = splitWithDelimiter(text);

        return Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(toList());
    }

    private static String[] splitWithDelimiter(String text) throws IncorrectInputFormatException {
        String delimiter = DEFAULT_DELIMITERS;
        if(text.startsWith(DELIMITER_PREFIX) && text.contains(DELIMITER_SUFFIX)) {
            delimiter = getCustomDelimiter(text);
            text = removeDefinitionOfCustomDelimiterFromTextBody(text, delimiter);
            if(delimiter.isEmpty())
                delimiter = DEFAULT_DELIMITERS;

            for (char chr : text.toCharArray()) {
                if(!(Character.isDigit(chr) || chr == delimiter.charAt(0))) {
                    System.out.println("Throw");
                    throw new IncorrectInputFormatException("'" + delimiter + "' expected but '" + chr + "' found at position " + text.indexOf(chr) + ".");
                }
            }
        }
        String delimiterForSplit = delimiter.length() == 1 && delimiter.matches("[^a-zA-Z0-9 ]")
                ? ESCAPE_SPECIAL_CHAR + delimiter : delimiter;
        return text.split(delimiterForSplit);
    }

    private static String getCustomDelimiter(String str) {
        return str.substring(DELIMITER_PREFIX.length(), str.indexOf(DELIMITER_SUFFIX));
    }

    private static String removeDefinitionOfCustomDelimiterFromTextBody(String text, String delimiter) {
        return text.substring(DELIMITER_PREFIX.length() + delimiter.length() + DELIMITER_SUFFIX.length());
    }
}
