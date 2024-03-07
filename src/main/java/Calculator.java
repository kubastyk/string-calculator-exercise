import java.util.Arrays;

public class Calculator {

    public int add(String... args) {
        return Arrays.stream(args).mapToInt(this::add).sum();
    }

    public int add(String numbers) {
        int result = 0;

        if(numbers.length() == 0)
            return 0;

        String[] splitNumbers = numbers.split(",");
        result = Arrays.stream(splitNumbers)
                .mapToInt(Integer::parseInt)
                .reduce(0, Integer::sum);

        return result;
    }
}
