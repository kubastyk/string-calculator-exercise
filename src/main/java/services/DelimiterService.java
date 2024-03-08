package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DelimiterService {

    public static final String DEFAULT_DELIMITERS = ",|\n";
    private static final String ESCAPE_SPECIAL_CHAR = "\\";

    public static List<Integer> extractNumbers(CalculationData calculationData) throws IncorrectInputFormatException {
        InputValidationService.validateSplittingInputByDelimiter(calculationData);

        String[] numbers = splitWithDelimiter(calculationData);
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
    }

    private static String[] splitWithDelimiter(CalculationData calculationData) {
        String delimiter = calculationData.delimiter();
        String delimiterForSplit = delimiter.length() == 1 && delimiter.matches("[^a-zA-Z0-9 ]")
                ? ESCAPE_SPECIAL_CHAR + delimiter : delimiter;
        return calculationData.numbers().split(delimiterForSplit);
    }
}
