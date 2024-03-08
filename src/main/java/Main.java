import exceptions.IncorrectInputFormatException;
import services.CalculatorService;

public class Main {

    public static void main(String[] args) {
        try {
            var calculator = new CalculatorService();
            calculator.add("//|\n1|2,-3");
        } catch(IncorrectInputFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
