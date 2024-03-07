package services;

import exceptions.IncorrectInputFormatException;

public class InputValidationService {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_DELIMITER = "\n";

    public static void verifyIfInputEndsWithDelimiter(String input) throws IncorrectInputFormatException {
        if(input.endsWith(COMMA_DELIMITER) || input.endsWith(NEW_LINE_DELIMITER))
            throw new IncorrectInputFormatException("Input data cannot end with the separator");
    }
}
