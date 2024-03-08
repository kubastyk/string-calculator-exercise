package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;

import java.util.List;

public class CalculatorService {

    private static final int ADD_MAX_LIMIT = 1000;

    public int add(String... args) throws IncorrectInputFormatException {
        int result = 0;
        for(String arg : args) {
            result += add(arg);
        }

        return result;
    }

    public int add(String numbers) throws IncorrectInputFormatException {
        if(numbers.length() == 0)
            return 0;

        InputValidationService.verifyIfInputEndsWithDelimiter(numbers);
        var calculationData = getCalculationData(numbers);

        List<Integer> extractedNumbers = DelimiterService.extractNumbers(calculationData);
        InputValidationService.verifyNegativeNumbers(extractedNumbers);

        return extractedNumbers.stream()
                .filter(num -> num <= ADD_MAX_LIMIT)
                .reduce(0, Integer::sum);
    }

    private CalculationData getCalculationData(String input) {
        var calculatorData = new CalculationData(input, DelimiterService.DEFAULT_DELIMITERS);
        if (CustomDelimiterService.checkIfContainsCustomDelimiterDefinition(input)) {
            calculatorData = CustomDelimiterService.split(input);
        }

        return calculatorData;
    }
}
