import java.util.ArrayList;
import java.util.List;

public class BankSystem {
    public static volatile List<Account> accounts = new ArrayList<>();
    public static int amountShouldBe = 0;


    public static void createExample() {
        amountShouldBe = 0;
        accounts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            accounts.add(new Account("Account: " + i, 1000));
            amountShouldBe += 1000;

        }

    }


    public static void print() {
        int totalAmount = 0;
        for (Account account : accounts) {
            System.out.println(account.getId() + " has: " + account.getAmount());
            totalAmount += account.getAmount();

        }
        System.out.println("There is: " + totalAmount + " there should be: " + amountShouldBe);
    }

    public static int getCurrent() {
        int totalAmount = 0;
        for (Account account : accounts) {
            totalAmount += account.getAmount();
        }
        return totalAmount;


    }
}
