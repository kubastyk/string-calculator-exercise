import services.CalculatorService;

public class Main {

    public static void main(String[] args) {
        var calculator = new CalculatorService();
        calculator.add("//|\n1|2,-3");
    }
}
