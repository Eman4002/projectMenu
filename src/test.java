import java.util.Scanner;

public class test {
    public static void testing() {
        Scanner input = new Scanner(System.in);
        String money = ("How much money do you have? ($0-1000)");
        System.out.println(money);

        int totalMoney;
        totalMoney = input.nextInt();

        if (totalMoney<= 1000) {
            System.out.println("Menu:");
        }
        else if(totalMoney > 1000) {
            System.out.println("ERROR: The amount you choose isn't " +
                    "within the limit. Please start over.");
        System.exit(0);
        }
    }
}
