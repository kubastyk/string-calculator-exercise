package services;

import exceptions.IncorrectInputFormatException;
import models.CalculatorData;

import java.util.Arrays;
import java.util.List;

public class CalculatorService {

    private static final int ADD_MAX_LIMIT = 1000;

    public int add(String... args) {
        return Arrays.stream(args).mapToInt(this::add).sum();
    }

    public int add(String numbers) {
        int result = 0;

        try {
            if(numbers.length() == 0)
                return 0;

            InputValidationService.verifyIfInputEndsWithDelimiter(numbers);

            var calculatorData = new CalculatorData(numbers, DelimiterService.DEFAULT_DELIMITERS);
            if (CustomDelimiterService.checkIfContainsCustomDelimiterDefinition(numbers)) {
                calculatorData = CustomDelimiterService.split(numbers);
            }
            List<Integer> extractedNumbers = DelimiterService.extractNumbers(calculatorData);
            InputValidationService.verifyNegativeNumbers(extractedNumbers);

            result = extractedNumbers.stream()
                    .filter(num -> num <= ADD_MAX_LIMIT)
                    .reduce(0, Integer::sum);
        } catch (IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
