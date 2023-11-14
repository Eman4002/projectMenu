





import java.util.Scanner;

// Class representing the Starters
class Starters {
    // Constants for the menu options
    public static final int OPTION_1 = 1;
    public static final int OPTION_2 = 2;
    public static final int OPTION_3 = 3;
    public static final int EXIT_OPTION = 0;

    // Static variable to keep track of the total money

   // this needs to be a question instead
    private static double totalMoney = 1000.0;  // Initial amount

    // Static method to get the total money
    public static double getTotalMoney() {
        return totalMoney;
    }

    // Static method to update the total money
    public static void updateTotalMoney(double amount) {
        totalMoney += amount;
    }

    // Method to display the menu
    public static void displayMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1. Option 1");
        System.out.println("2. Option 2");
        System.out.println("3. Option 3");
        System.out.println("0. Exit");
        System.out.println("==============");
    }
}

// Class representing the Menu
class Menu {
    // Method to handle user input and process menu options
    public void processMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            Starters.displayMenu();

            // Get user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Process user choice using switch statement
            switch (choice) {
                case Starters.OPTION_1:
                    // Handle option 1
                    System.out.println("Option 1 selected.");
                    break;
                case Starters.OPTION_2:
                    // Handle option 2
                    System.out.println("Option 2 selected.");
                    break;
                case Starters.OPTION_3:
                    // Handle option 3
                    System.out.println("Option 3 selected.");
                    break;
                case Starters.EXIT_OPTION:
                    // Exit the program
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Main class to start the program
public class Main {
    public static void main(String[] args) {
        // Create an instance of the Menu class
        Menu menu = new Menu();

        // Start processing the menu
        menu.processMenu();
    }
}
