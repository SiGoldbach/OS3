import java.util.ArrayList;
import java.util.List;

public class BankSystem {
    public static List<Account> accounts = new ArrayList<>();


    public static void createExample() {
        accounts.add(new Account("Account1", 1000));
        accounts.add(new Account("Account2", 1000));
        accounts.add(new Account("Account3", 1000));
        accounts.add(new Account("Account4", 1000));
        accounts.add(new Account("Account5", 1000));

    }

    public static void print() {
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).getId() + " has: " + accounts.get(i).getAmount());

        }
    }
}
