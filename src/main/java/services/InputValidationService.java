package services;

import exceptions.IncorrectInputFormatException;

public class InputValidationService {

    private static final String DEFAULT_DELIMITERS = ",|\n";

    public static void verifyIfInputEndsWithDelimiter(String input) throws IncorrectInputFormatException {
        if(input.endsWith(DEFAULT_DELIMITERS))
            throw new IncorrectInputFormatException("Input data cannot end with the separator");
    }
}
