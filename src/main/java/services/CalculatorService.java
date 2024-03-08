package services;

import exceptions.IncorrectInputFormatException;
import models.CalculationData;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {

    private static final int ADD_MAX_LIMIT = 1000;

    public int add(String... args) {
        int result = 0;
        for(String arg : args) {
            result += add(arg);
        }

        return result;
    }

    public int add(String input) {
        if(input.length() == 0)
            return 0;

        List<Integer> extractedNumbers = extractAndValidateData(input);
        return extractedNumbers.stream()
                .filter(num -> num <= ADD_MAX_LIMIT)
                .reduce(0, Integer::sum);
    }

    private List<Integer> extractAndValidateData(String input) {
        var extractedNumbers = new ArrayList<Integer>();
        try {
            var calculationData = getCalculationData(input);
            verifyIfInputEndsWithDelimiter(calculationData);

            extractedNumbers = (ArrayList<Integer>) DelimiterService.extractNumbers(calculationData);
            InputValidationService.verifyNegativeNumbers(extractedNumbers);
        } catch (IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }

        return extractedNumbers;
    }

    private CalculationData getCalculationData(String input) {
        var calculatorData = new CalculationData(input, DelimiterService.DEFAULT_DELIMITERS);
        if (CustomDelimiterService.checkIfContainsCustomDelimiterDefinition(input)) {
            calculatorData = CustomDelimiterService.split(input);
        }

        return calculatorData;
    }

    private void verifyIfInputEndsWithDelimiter(CalculationData calculationData) {
        try {
            InputValidationService.verifyIfInputEndsWithDelimiter(calculationData);
        } catch (IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
