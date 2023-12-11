
/* Group 5 / Project Menu
Prof. Shiva Sharma
10/12/2023
Eman Hussien
Esan Khaial
Jocelyn Le
Tanaya Adebusoye
Veronica Amaya
*/


                           /* imported */
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


                         // Abstract class representing the MenuItems
abstract class MenuItems {
    protected String itemName;
    protected BigDecimal itemPrice;

    public MenuItems(String itemName, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }


          // Abstract method  implemented by subclasses
    public abstract void displayItemInfo();
}


        // Class for the Starters / foods given number values
class Starters extends MenuItems {
    public static final int MAC_AND_CHEESE = 1;
    public static final int SPAGHETTI = 2;
    public static final int FRENCH_FRIES = 3;
    public static final int CHICKEN_SANDWICH = 4;
    public static final int COOKIES = 5;
    public static final int BROWNIE = 6;
    public static final int PIE = 7;
    public static final int EXIT_OPTION = 0;

    private static BigDecimal totalMoney = new BigDecimal("15.0");

    public static BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public static void updateTotalMoney(BigDecimal amount) {
        totalMoney = totalMoney.add(amount);
    }



              // the menu and prices being displayed for the user
    public static void displayMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1. Mac and Cheese - $10.99");
        System.out.println("2. Spaghetti - $8.50");
        System.out.println("3. French Fries - $12.75");
        System.out.println("4. Chicken Sandwich - $9.99");
        System.out.println("5. Cookies - $5.50");
        System.out.println("6. Brownie - $7.25");
        System.out.println("7. Pie - $15.00");
        System.out.println("0. Exit");
        System.out.println("==============");
    }


                   // Constructor
    public Starters(String itemName, BigDecimal itemPrice) {
        super(itemName, itemPrice);
    }


                       // the abstract method
    @Override
    public void displayItemInfo() {
        System.out.println(getItemName() + " - $" + getItemPrice());
    }


                          // static method to for each  item price
    public static BigDecimal getItemPrice(int choice) {
        switch (choice) {
            case MAC_AND_CHEESE:
                return new BigDecimal("10.99");
            case SPAGHETTI:
                return new BigDecimal("8.50");
            case FRENCH_FRIES:
                return new BigDecimal("12.75");
            case CHICKEN_SANDWICH:
                return new BigDecimal("9.99");
            case COOKIES:
                return new BigDecimal("5.50");
            case BROWNIE:
                return new BigDecimal("7.25");
            case PIE:
                return new BigDecimal("15.00");
            default:
                throw new IllegalArgumentException("Invalid choice: " + choice);
        }
    }
}


                        //  Menu Class
class Menu {
    private Scanner scanner;
    private List<String> selectedItems;
    private BigDecimal initialMoney;

   // scanners for array list , initial money
    public Menu(BigDecimal initialMoney) {
        this.scanner = new Scanner(System.in);
        this.selectedItems = new ArrayList<>();
        this.initialMoney = initialMoney;
    }


                           // process menu restrictions
    public void processMenu() {
        try {
            Starters.updateTotalMoney(initialMoney);
            int choice;
            boolean done = false;

            do {
                Starters.displayMenu();

                // user choice after checking it's one of the integers allowed
                while (true) {
                    System.out.print("Enter your choice: ");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // consume the invalid input
                    }
                }


                         //  user choice by switch statement
                switch (choice) {
                    case Starters.MAC_AND_CHEESE:
                    case Starters.SPAGHETTI:
                    case Starters.FRENCH_FRIES:
                    case Starters.CHICKEN_SANDWICH:
                    case Starters.COOKIES:
                    case Starters.BROWNIE:
                    case Starters.PIE:
                        processOption(choice);
                        break;
                    case Starters.EXIT_OPTION:
                        System.out.println("Exiting the program. Goodbye!");
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }


                           // shows the amount of money left
                System.out.println("Remaining Money: $" + Starters.getTotalMoney());


                // checks the money if there is sufficient funds or not
                if (Starters.getTotalMoney().compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("ERROR: Insufficient funds. Exiting the program.");
                    done = true;
                }


                                 // if statement that checks if the user is done eating
                if (!done) {
                    System.out.print("Type 'done' if you're done, otherwise press Enter to continue: ");
                    scanner.nextLine(); // Consume the newline character left by nextInt()
                    String doneInput = scanner.nextLine().trim().toLowerCase();
                    if (!doneInput.equals("done") && !doneInput.isEmpty()) {
                        System.out.println("ERROR: Invalid input. Exiting the program.");
                        done = true;
                    } else if (doneInput.equals("done")) {
                        displayReceipt();
                        done = true;
                    }
                }

                  //system shows invalid input
            } while (!done);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }

                    //processes the money
                            // gives the user what they asked for and tells them enjoy
    private void processOption(int option) {
        BigDecimal price = Starters.getItemPrice(option);
        if (price.compareTo(Starters.getTotalMoney()) <= 0) {
            Starters selectedStarter = new Starters("", BigDecimal.ZERO);   //temporary instance
            selectedItems.add(selectedStarter.getItemName());
            System.out.println("Option " + option + " selected. Price: $" + price);
            Starters.updateTotalMoney(price.negate());     // Subtract the price from totalMoney
            System.out.println("Here is your food" + selectedStarter.getItemName() + " - Enjoy!");
        } else {
            System.out.println("ERROR: Insufficient funds. Please choose a cheaper option.");
        }
    }

                      // gives the user their receipt
    private void displayReceipt() {
        System.out.println("\n==== Receipt ====");
        for (String item : selectedItems) {
            System.out.println(item);
        }
        System.out.println("Total cost: $" + initialMoney.subtract(Starters.getTotalMoney()));
        System.out.println("=================");
    }
}

                          /* Main class to start the program*/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How much money do you have? ($0-15)");

        double totalMoney = input.nextDouble();

        if (totalMoney >= 0 && totalMoney <= 15) {
                // an instance of the Menu class
            Menu menu = new Menu(new BigDecimal(totalMoney));

             // Start processing the menu
            menu.processMenu();
        } else {
            System.out.println("ERROR: The amount you chose isn't within the limit provided. Please start over.");
        }

    }
}
