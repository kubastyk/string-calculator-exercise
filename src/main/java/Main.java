import services.CalculatorService;

public class Main {

    public static void main(String[] args) {
        var calculator = new CalculatorService();

        /**
         * I think there might be a logical issue at point 7 of the exercise:
         * Example input: "//|\n1|2,-3"
         * - in this case we getting information that the separator is '|'
         * - when we split the data by this separator we receive: '1' & '2,-3'
         * - since we should treat second value as a result of separation by diameter
         *   we are not able to display information about negative integer since it is not a number
         */
        calculator.add("//|\n1|2,-3");
        System.out.println("---Valid example of displaying multiple exception messages---");
        calculator.add("//|\n1|2|-3|");
    }
}
