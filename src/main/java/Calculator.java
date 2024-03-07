import exceptions.IncorrectInputFormatException;
import services.InputValidationService;

import java.util.Arrays;

public class Calculator {

    private static final String DEFAULT_DELIMITERS = ",|\n";

    public int add(String... args) {
        return Arrays.stream(args).mapToInt(this::add).sum();
    }

    public int add(String numbers) {
        int result = 0;

        try {
            if(numbers.length() == 0)
                return 0;

            InputValidationService.verifyIfInputEndsWithDelimiter(numbers);

            String[] splitNumbers = numbers.split(DEFAULT_DELIMITERS);
            result = Arrays.stream(splitNumbers)
                    .mapToInt(Integer::parseInt)
                    .reduce(0, Integer::sum);
        } catch (IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
