package machine;
import java.util.Scanner;

public class Machine {

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private StringBuilder collection;

    public Machine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
        this.collection = new StringBuilder();
    }

    public static void userChoice(String choice, Machine machine, StringBuilder looping) {
        Scanner scanner = new Scanner(System.in);
        if (choice.equals("fill")) {
            System.out.println("\nWrite how many ml of water you want to add:");
            machine.addWater(scanner.nextInt());
            System.out.println("Write how many ml of milk you want to add:");
            machine.addMilk(scanner.nextInt());
            System.out.println("Write how many grams of coffee beans you want to add:");
            machine.addBeans(scanner.nextInt());
            System.out.println("Write how many disposable cups you want to add:");
            machine.addCups(scanner.nextInt());
        } else if (choice.equals("buy")) {
            System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            switch (scanner.nextLine()) {
                case "1":
                    if (machine.isEnough("1").isEmpty()) {
                        System.out.println("I have enough resources, making you a coffee!");
                        machine.espresso();
                    } else {
                        System.out.printf("Sorry, not enough %s!", machine.isEnough("1"));
                    }
                    break;
                case "2":
                    if (machine.isEnough("2").isEmpty()) {
                        System.out.println("I have enough resources, making you a coffee!");
                        machine.latte();
                    } else {
                        System.out.printf("Sorry, not enough %s!", machine.isEnough("2"));
                    }
                    break;
                case "back":
                    break;
                case "3":
                    if (machine.isEnough("3").isEmpty()) {
                        System.out.println("I have enough resources, making you a coffee!");
                        machine.cappuccino();
                    } else {
                        System.out.printf("Sorry, not enough %s!", machine.isEnough("3"));
                    }
                    break;
                default:
                    System.out.println("invalid");
            }
        } else if (choice.equals("take")) {
            System.out.println();
            machine.giveMoney();
        } else if (choice.equals("remaining")) {
            System.out.println("\n" + machine);
        } else if (choice.equals("exit")) {
            looping.setLength(0);
        }
    }

    public int getWater() {
        return water;
    }

    public void addWater(int add) {
        water += add;
    }

    public int getMilk() {
        return milk;
    }

    public void addMilk(int add) {
        milk += add;
    }

    public int getBeans() {
        return beans;
    }

    public void addBeans(int add) {
        beans += add;
    }

    public int getCups() {
        return cups;
    }

    public void addCups(int add) {
        cups += add;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int add) {
        money += add;
    }

    public void giveMoney() {
        System.out.println("I gave you $" + this.getMoney());
        money = 0;
    }

    public void espresso() {
        water -= 250;
        beans -= 16;
        cups -= 1;
        this.addMoney(4);
    }

    public void latte() {
        water -= 350;
        milk -= 75;
        beans -= 20;
        cups -= 1;
        this.addMoney(7);
    }

    public void cappuccino() {
        water -= 200;
        milk -= 100;
        beans -= 12;
        cups -= 1;
        this.addMoney(6);
    }

    public String isEnough(String string) {
        this.collection.setLength(0);
        switch (string) {
            case "1":
                if (water < 250) {
                    collection.append(" water");
                }
                if (beans < 16) {
                    collection.append(" beans");
                }
                if (cups < 1) {
                    collection.append(" cups");
                }
                break;
            case "2":
                if (water < 350) {
                    collection.append(" water");
                }
                if (milk < 75) {
                    collection.append(" milk");
                }
                if (beans < 20) {
                    collection.append(" beans");
                }
                if (cups < 1) {
                    collection.append(" cups");
                }
                break;
            case "3":
                if (water < 200) {
                    collection.append(" water");
                }
                if (milk < 100) {
                    collection.append(" milk");
                }
                if (beans < 12) {
                    collection.append(" beans");
                }
                if (cups < 1) {
                    collection.append(" cups");
                }
                break;
        }
        return collection.toString().trim().replaceAll(" ", " and ");
    }

    public String toString() {
        String state = String.format("%d ml of water%n%d ml of milk%n%d g of coffee beans" +
                "%n%d disposable cups%n$%d of money", water, milk, beans, cups, money);
        return String.format("The coffee machine has:%n%s", state);
    }
}
