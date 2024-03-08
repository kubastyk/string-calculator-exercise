import exceptions.IncorrectInputFormatException;
import models.CalculatorData;
import services.CustomDelimiterService;
import services.DelimiterService;
import services.InputValidationService;

import java.util.Arrays;
import java.util.List;

public class Calculator {

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
            List<Integer> splitNumbers = DelimiterService.extractNumbers(calculatorData);

            result = splitNumbers.stream()
                    .reduce(0, Integer::sum);
        } catch (IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
