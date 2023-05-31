package converter;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("/exit")) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            choice = scanner.nextLine();
            switch (choice) {
                case "/exit":
                    break;
                default:
                    converter(choice.split("\\s"));
            }
        }
    }

    public static void converter(String[] list) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int source = Integer.parseInt(list[0]);
        int target = Integer.parseInt(list[1]);
        boolean useBigDecimal = false;
        while (true) {
            System.out.printf("Enter number in base %d to convert to base %d (To go back type /back)%n", source, target);
            input = scanner.nextLine();
            for (char c : input.toCharArray()) {
                if (c == '.') {
                    useBigDecimal = true;
                    break;
                }
            }
            if (input.equals("/back")) break;

            if (useBigDecimal) {
                String[] twoPart = input.split("\\.");
                BigInteger firstPart = new BigInteger(twoPart[0], source);
                BigDecimal secondPart = new BigDecimal("0.0");
                int count = -1;
                for (String s : twoPart[1].split("")) {
                    secondPart = secondPart.add(new BigDecimal(Math.pow(source , count) * Integer.parseInt(s, source)));
                    count--;
                    if (count < -16) break;
                }
                double quotient = (secondPart.doubleValue() * Math.pow(target, 8));
                List<Integer> addList = new ArrayList<>();
                while (quotient >= 1) {
                    addList.add((int) Math.floor(quotient % target));
                    quotient /= target;
                }
                while (addList.size() < 8) {
                    addList.add(0);
                }
                StringBuilder builder = new StringBuilder();
                for (int i = addList.size() - 1; i >= 0; i--) {
                    builder.append(Integer.toString(addList.get(i), target));
                }
                while (builder.length() < 5) {
                    builder.append(0);
                }
                System.out.print("Conversion result: ");
                System.out.println(firstPart.toString(target) + "." + builder.substring(0, 5).toString());

            } else {
                BigInteger inputBig = new BigInteger(input, source);
                System.out.printf("Conversion result: %s%n", inputBig.toString(target));
            }
            useBigDecimal = false;
        }
    }
}