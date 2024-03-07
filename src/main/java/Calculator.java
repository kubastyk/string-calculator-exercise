import exceptions.IncorrectInputFormatException;
import services.CustomDelimiterService;
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
            List<Integer> splitNumbers = CustomDelimiterService.split(numbers);

            result =splitNumbers.stream()
                    .reduce(0, Integer::sum);
        } catch (IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
