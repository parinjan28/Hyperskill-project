package numbers;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean even; boolean buzz; boolean duck; boolean palin; boolean gap; boolean spy; boolean square; boolean sunny; boolean jump;
        boolean happy; boolean sad;
        String[] options = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD",
        "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY", "-EVEN", "-ODD", "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        printInstructions();
        while (true) {
            System.out.println("Enter a request:");
            String userInput = scanner.nextLine();
            String[] userList = userInput.split(" ");
            if (!checkInput(userList, options) || userInput.equals("0") || userInput.isEmpty()) {
                if (userInput.equals("0")) break;
                else if (userInput.isEmpty()) printInstructions();
            } else {
                long userInt = Long.valueOf(userList[0]);
                int consecutive;
                if (userList.length == 2) {
                    consecutive = Integer.valueOf(userList[1]);
                    for (int i = 0; i < consecutive; i++) {
                        printList(userInt + i);
                    }
                } else if (userList.length == 1) {
                    even = evenParity(userInt);
                    buzz = checkBuzz(userInt);
                    duck = checkDuck(userInt);
                    palin = checkPalindrome(userInt);
                    gap = checkGapful(userInt);
                    spy = checkSpy(userInt);
                    square = checkSquare(userInt);
                    sunny = checkSunny(userInt);
                    jump = checkJump(userInt);
                    happy = checkHappy(userInt);
                    sad = !checkHappy(userInt);
                    System.out.printf("Properties of %s%neven: %b%nodd: %b%nbuzz:" +
                                    " %b%nduck: %b%npalindromic: %b%ngapful: %b%nspy: %b%n" +
                                    "square: %b%nsunny: %b%njumping: %b%nhappy: %b%nsad: %b%n"
                            , String.format("%,d", userInt), even, !even, buzz, duck
                            , palin, gap, spy, square, sunny, jump, happy, sad);
                } else {
                    consecutive = Integer.valueOf(userList[1]);
                    int count = 0;
                    ArrayList<String> word = new ArrayList<>();
                    for (int i = 2; i < userList.length; i++) {
                        if (!word.contains(userList[i].toUpperCase())) word.add(userList[i].toUpperCase());
                    }
                    printComplex(consecutive, count, userInt, word);
                }
            }
        }
        System.out.println("Goodbye!");
    }

    public static void printComplex(int consecutive, int count, long num, ArrayList<String> word) {
        HashMap<String, Boolean> checker = new HashMap<>();
        StringBuilder placeHolder = new StringBuilder();
        boolean check;
        while (count < consecutive) {
            check = true;
            checker.put("BUZZ", checkBuzz(num));
            checker.put("DUCK", checkDuck(num));
            checker.put("PALINDROMIC", checkPalindrome(num));
            checker.put("GAPFUL", checkGapful(num));
            checker.put("SPY", checkSpy(num));
            checker.put("EVEN", evenParity(num));
            checker.put("ODD", !evenParity(num));
            checker.put("SQUARE", checkSquare(num));
            checker.put("SUNNY", checkSunny(num));
            checker.put("JUMPING", checkJump(num));
            checker.put("HAPPY", checkHappy(num));
            checker.put("SAD", !checkHappy(num));

            for (String s : word) {
                if (s.charAt(0) != '-') {
                    if (!checker.get(s)) {
                        check = false;
                        break;
                    }
                } else {
                    placeHolder.append(s);
                    if (checker.get(placeHolder.substring(1))) {
                        placeHolder.setLength(0);
                        check = false;
                        break;
                    }
                    placeHolder.setLength(0);
                }
            }
            if (check) {
                printList(num);
                count++;
            }
            num++;
        }
    }

    public static void printList(long number) {
        StringBuilder builder = new StringBuilder(String.format("%,d", number) + " is ");
        builder.append(evenParity(number) ? "even" : "odd");
        if (checkBuzz(number)) builder.append(", buzz");
        if (checkDuck(number)) builder.append(", duck");
        if (checkPalindrome(number)) builder.append(", palindromic");
        if (checkGapful(number)) builder.append(", gapful");
        if (checkSpy(number)) builder.append(", spy");
        if (checkSquare(number)) builder.append(", square");
        if (checkSunny(number)) builder.append(", sunny");
        if (checkJump(number)) builder.append(", jumping");
        if (checkHappy(number)) builder.append(", happy");
        if (!checkHappy(number)) builder.append(", sad");
        System.out.println(builder.toString());
    }

    public static boolean checkInput(String[] s, String[] options) {
        String[] cleanOptions = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"};
        for (char c : s[0].toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("The first parameter should be a natural number or zero.");
                return false;
            }
        }
        if (s.length >= 2) {
            for (char c : s[1].toCharArray()) {
                if (!Character.isDigit(c)) {
                    System.out.println("The second parameter should be a natural number.");
                    return false;
                }
            }
        }
        HashSet<String> rightList = new HashSet<>();
        HashSet<String> userList = new HashSet<>();
        if (s.length >= 3) {
            for (int i = 2; i < s.length; i++) {
                for (String c : options) {
                    if (c.equalsIgnoreCase(s[i])) {
                        rightList.add(s[i].toUpperCase());
                    }
                }
                userList.add(s[i].toUpperCase());
            }
            if (rightList.size() < userList.size()) {
                StringBuilder errorPrint = new StringBuilder();
                for (int i = 2; i < s.length; i++) {
                    if (!rightList.contains(s[i].toUpperCase())) {
                        if (!errorPrint.isEmpty()) {
                            errorPrint.append(", ").append(s[i].toUpperCase());
                        } else {
                            errorPrint.append(s[i].toUpperCase());
                        }
                    }
                }
                if (rightList.size() == userList.size() - 1) {
                    System.out.printf("The property [%s] is wrong.%nAvailable properties: ", errorPrint.toString());
                    System.out.println(Arrays.toString(cleanOptions));
                } else {
                    System.out.printf("The properties [%s] are wrong.%nAvailable properties: ", errorPrint.toString());
                    System.out.println(Arrays.toString(cleanOptions));
                }
                return false;
            } else {
                return !checkMutuallyExclusive(rightList);
            }
        }
        return true;
    }

    public static boolean checkMutuallyExclusive(HashSet<String> list) {
        String negative;
        boolean checkEvenOdd = false;
        boolean checkDuckSpy = false;
        boolean checkSunnySquare = false;
        boolean checkHappySad = false;
        boolean checkOppo = false;
        StringBuilder builder = new StringBuilder();
        if (list.contains("EVEN") && list.contains("ODD") || list.contains("-EVEN") && list.contains("-ODD")) {
            checkEvenOdd = true;
            if (list.contains("EVEN")) builder.append("EVEN, ODD");
            else builder.append("-EVEN, -ODD");
        }
        if (list.contains("DUCK") && list.contains("SPY")) {
            checkDuckSpy = true;
            if (builder.isEmpty()) {
                if (list.contains("DUCK")) builder.append("DUCK, SPY");
                else builder.append("-DUCK, -SPY");
            }
            else {
                if (list.contains("DUCK")) builder.append(", DUCK, SPY");
                else builder.append(", -DUCK, -SPY");
            }
        }
        if (list.contains("SUNNY") && list.contains("SQUARE")) {
            checkSunnySquare = true;
            if (builder.isEmpty()) {
                if (list.contains("SUNNY")) builder.append("SUNNY, SQUARE");
                else builder.append("-SUNNY, -SQUARE");
            }
            else {
                if (list.contains("SUNNY")) builder.append(", SUNNY, SQUARE");
                else builder.append(", -SUNNY, -SQUARE");
            }
        }
        if (list.contains("HAPPY") && list.contains("SAD") || list.contains("-HAPPY") && list.contains("-SAD")) {
            checkHappySad = true;
            if (builder.isEmpty()) {
                if (list.contains("HAPPY")) builder.append("HAPPY, SAD");
                else builder.append("-HAPPY, -SAD");
            }
            else {
                if (list.contains("HAPPY")) builder.append(", HAPPY, SAD");
                else builder.append(", -HAPPY, -SAD");
            }
        }
        for (String s : list) {
            negative = String.format("-%s", s);
            if (list.contains(negative)) {
                checkOppo = true;
                if (builder.isEmpty()) {
                    builder.append(String.format("%s, %s", s, negative));
                }
            }
        }
        if (checkDuckSpy || checkEvenOdd || checkSunnySquare || checkHappySad || checkOppo) {
            System.out.printf("The request contains mutually exclusive properties: [%s]\n" +
                    "There are no numbers with these properties.%n", builder.toString());
            return true;
        }
        else return false;
    }

    public static boolean evenParity(long number) {
        return number % 2 == 0;
    }

    public static boolean checkPalindrome(long number) {
        String intString = Long.toString(number);
        String reverseString = new StringBuilder(intString).reverse().toString();
        return intString.equals(reverseString);
    }

    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    public static boolean checkDuck(long input) {
        String intString = Long.toString(input);
        for (int i = 0; i < intString.length(); i++) {
            if (intString.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBuzz(long input) {
        String intString = Long.toString(input);
        if (intString.charAt(intString.length() - 1) == '7') {
            return true;
        } else {
            return input % 7 == 0;
        }
    }

    public static boolean checkGapful(long input) {
        if (input < 100) return false;
        else {
            StringBuilder firstLast = new StringBuilder();
            String inputString = Long.toString(input);
            firstLast.append(inputString.charAt(0));
            firstLast.append(inputString.charAt(inputString.length() - 1));
            return input % Integer.valueOf(firstLast.toString()) == 0;
        }
    }

    public static boolean checkSpy(long input) {
        String intString = Long.toString(input);
        int num;
        int product = 1;
        int sum = 0;
        for (char c : intString.toCharArray()) {
            num = Character.getNumericValue(c);
            product *= num;
            sum += num;
        }
        return product == sum;
    }

    public static boolean checkSquare(long input) {
        return Math.sqrt(input) == Math.floor(Math.sqrt(input));
    }
    public static boolean checkSunny(long input) {
        long perfectSquare = input + 1;
        return checkSquare(perfectSquare);
    }

    public static boolean checkJump(long input) {
        boolean check = true;
        String inputString = Long.toString(input);
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (Math.abs(Character.getNumericValue(inputString.charAt(i))
                    - Character.getNumericValue(inputString.charAt(i + 1))) != 1) {
                check = false; break;
            }
        }
        return check;
    }

    public static boolean checkHappy(long input) {
        String inputString;
        ArrayList<Long> memories = new ArrayList<>();
        memories.add(input);
        long sum;
        while (true) {
            sum = 0;
            inputString = Long.toString(input);
            for (char c : inputString.toCharArray()) {
                sum += Math.pow(Character.getNumericValue(c), 2);
            }
            if (sum == 1) return true;
            else if (memories.contains(sum)) return false;
            memories.add(sum);
            input = sum;
        }
    }
}
