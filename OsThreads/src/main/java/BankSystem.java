import java.util.ArrayList;
import java.util.List;

public class BankSystem {
    public static List<Account> accounts = new ArrayList<>();
    public static int amountShouldBe = 0;


    public static void createExample() {
        accounts = new ArrayList<>();
        accounts.add(new Account("Account1", 1000));
        accounts.add(new Account("Account2", 1000));
        accounts.add(new Account("Account3", 1000));
        accounts.add(new Account("Account4", 1000));
        accounts.add(new Account("Account5", 1000));
        amountShouldBe = 5000;

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
