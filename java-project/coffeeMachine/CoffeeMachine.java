package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine coffeeMachine = new Machine();
        Scanner scanner = new Scanner(System.in);
        StringBuilder looping = new StringBuilder("true");
        while (looping.toString().equals("true")) {
            System.out.printf("Write action (buy, fill, take, remaining, exit):%n");
            Machine.userChoice(scanner.nextLine(), coffeeMachine, looping);
            System.out.println();
        }
    }
}
