package chucknorris;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String userInput = scanner.nextLine();
            if (userInput.equals("encode")) {
                encode();
            } else if (userInput.equals("decode")) {
                decode();
            } else if (userInput.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.printf("There is no '%s' operation%n", userInput);
            }
        }
    }
    public static void encode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        char[] userInput = scanner.nextLine().toCharArray();
        String userBinary = "";
        for (char c : userInput) {
            userBinary += String.format("%7s", Integer.toBinaryString(c)).replace(" ", "0");
        }
        String chuckNorrisString = "";
        String subString = "";
        for (int i = 0; i < userBinary.length(); i++) {
            if (i == 0) {
                if (userBinary.charAt(0) == '0') {
                    subString = "00 0";
                } else {
                    subString = "0 0";
                }
            }
            if (i != 0) {
                if (userBinary.charAt(i) == userBinary.charAt(i - 1)) {
                    subString += "0";
                } else {
                    chuckNorrisString += subString;
                    if (userBinary.charAt(i) == '0') {
                        subString = " 00 0";
                    } else {
                        subString = " 0 0";
                    }
                }
            }
        }
        chuckNorrisString += subString;
        System.out.printf("Encoded string:%n%s%n",chuckNorrisString);
    }

    public static void decode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String[] userInput = scanner.nextLine().split(" ");
        String subString = "";
        String binary = "";
        String decoded = "";
        boolean valid = userInput.length % 2 == 0 ? true : false;
        if (valid) {
            for (int i = 0; i < userInput.length; i++) {
                if (i % 2 == 0) {
                    if (userInput[i].equals("00")) {
                        subString = "0";
                    } else if (userInput[i].equals("0")) {
                        subString = "1";
                    } else {
                        valid = false;
                    }
                } else if (i % 2 == 1 && valid) {
                    for (int j = 0; j < userInput[i].length(); j++) {
                        if (userInput[i].charAt(j) == '0') {
                            binary += subString;
                            if (binary.length() == 7) {
                                decoded += (char) Integer.parseInt(binary, 2);
                                binary = "";
                            }
                        } else {
                            valid = false;
                        }
                    }
                }
            }
        }
        if (!binary.isEmpty()) {
            valid = false;
        }
        if (valid) {
            System.out.printf("Decoded string:%n%s%n", decoded);
        } else {
            System.out.println("Encoded string is not valid.");
        }
    }
}