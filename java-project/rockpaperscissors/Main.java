package rockpaperscissors;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] computerList;
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int computerRandom;
        String userChoice = null;
        String computerChoice;
        String result;
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        int userRating = 0;
        System.out.println("Hello, " + userName);
        String allChoices = scanner.nextLine();
        if (allChoices.isEmpty()) {
            computerList = new String[] {"rock", "paper", "scissors"};
        } else {
            computerList = allChoices.split(",");
        }
        for (String s : computerList) {
            list.add(s);
        }
        try {
            String userStat = new String(Files.readAllBytes(Paths.get("rating.txt")));
            Scanner myFile = new Scanner(userStat);
            String[] fileName;
            while(myFile.hasNextLine()) {
                fileName = myFile.nextLine().split(" ");
                if (userName.equals(fileName[0])) {
                    userRating = Integer.parseInt(fileName[1]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Okay, let's start");
       while (!"!exit".equals(userChoice)) {
            userChoice = scanner.nextLine();
            computerRandom = random.nextInt(list.size());
            computerChoice = list.get(computerRandom);
            result = checkWin(list, userChoice, computerChoice);
            if (result.equals("Draw")) {
                System.out.printf("There is a draw (%s)%n", userChoice);
                userRating += 50;
            } else if (result.equals("Lose")) {
                System.out.println("Sorry, but the computer chose " + computerChoice);
            } else if (result.equals("Win")){
                System.out.printf("Well done. The computer chose %s and failed%n", computerChoice);
                userRating += 100;
            } else if (userChoice.equals("!rating")) {
                System.out.printf("Your rating: %d%n", userRating);
            } else if (!userChoice.equals("!exit")) {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Bye!");
    }

    public static String checkWin(ArrayList<String> choices, String user, String computer) {
        int computerIndex;
        if (!choices.contains(user)) {
            return "no result";
        } else if (choices.indexOf(user) + 1 > (choices.size() + 1) / 2) {
            computerIndex = choices.indexOf(computer) < choices.indexOf(user) - choices.size() / 2 ? choices.indexOf(computer) + choices.size() : choices.indexOf(computer);
            return choices.indexOf(user) > computerIndex ? "Win" : choices.indexOf(user) < computerIndex ? "Lose" : "Draw";
        } else if (choices.indexOf(user) + 1 == (choices.size() + 1) / 2) {
            return choices.indexOf(user) > choices.indexOf(computer) ? "Win" : choices.indexOf(user) < choices.indexOf(computer) ? "Lose" : "Draw";
        } else {
            computerIndex = choices.indexOf(computer) > choices.indexOf(user) + choices.size() / 2 ? choices.indexOf(computer) - choices.size() : choices.indexOf(computer);
            return choices.indexOf(user) > computerIndex ? "Win" : choices.indexOf(user) < computerIndex ? "Lose" : "Draw";
        }
    }
}
