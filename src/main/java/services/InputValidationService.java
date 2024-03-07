package services;

import exceptions.IncorrectInputFormatException;

public class InputValidationService {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_DELIMITER = "\n";

    public static void verifyIfInputEndsWithDelimiter(String input) throws IncorrectInputFormatException {
        if(input.endsWith(COMMA_DELIMITER) || input.endsWith(NEW_LINE_DELIMITER))
            throw new IncorrectInputFormatException("Input data cannot end with the separator");
    }

    public static void validateInputWithCustomDelimiter(String input, String delimiter) throws IncorrectInputFormatException {
        for (char chr : input.toCharArray()) {
            if(!(Character.isDigit(chr) || chr == delimiter.charAt(0))) {
                throw new IncorrectInputFormatException("'%s' expected but '%s' found at position %s."
                        .formatted(delimiter, chr, input.indexOf(chr)));
            }
        }
    }
}
