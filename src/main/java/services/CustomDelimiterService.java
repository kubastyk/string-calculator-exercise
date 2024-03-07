package services;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CustomDelimiterService {

    private static final String DEFAULT_DELIMITERS = ",|\n";
    private static final String DELIMITER_PREFIX = "//";
    private static final String DELIMITER_SUFFIX = "\n";
    private static final String ESCAPE_SPECIAL_CHAR = "\\";

    public static List<Integer> split(String text) {
        String[] numbersString = splitWithDelimiter(text);

        return Arrays.stream(numbersString)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
    }

    private static String[] splitWithDelimiter(String text) {
        String delimiter = DEFAULT_DELIMITERS;
        if(text.startsWith(DELIMITER_PREFIX)) {
            delimiter = getCustomDelimiter(text);
            text = removeDefinitionOfCustomDelimiterFromTextBody(text, delimiter);
            if(delimiter.isEmpty())
                delimiter = DEFAULT_DELIMITERS;

            if(delimiter.length() == 1 && delimiter.matches("[^a-zA-Z0-9 ]"))
                delimiter = ESCAPE_SPECIAL_CHAR + delimiter;
        }

        return text.split(delimiter);
    }

    private static String getCustomDelimiter(String str) {
        return str.substring(DELIMITER_PREFIX.length(), str.indexOf(DELIMITER_SUFFIX));
    }

    private static String removeDefinitionOfCustomDelimiterFromTextBody(String text, String delimiter) {
        return text.substring(DELIMITER_PREFIX.length() + delimiter.length() + DELIMITER_SUFFIX.length());
    }
}
