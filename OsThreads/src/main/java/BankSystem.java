import java.util.ArrayList;
import java.util.List;

public class BankSystem {
    public static LogBuilder logBuilder;
    public static volatile List<Account> accounts = new ArrayList<>();
    public static int amountShouldBe = 0;
    public static int transferAmount = 0;


    public static void createExample(int accountAmount, int transfers, int amount, String logName) {
        amountShouldBe = 0;
        accounts = new ArrayList<>();
        for (int i = 0; i < accountAmount; i++) {
            accounts.add(new Account("Account: " + i, amount));
            amountShouldBe += amount;
            transferAmount = transfers;

        }
        logBuilder = new LogBuilder(logName, accountAmount);

    }


    public static int getCurrent() {
        int totalAmount = 0;
        for (Account account : accounts) {
            totalAmount += account.getAmount();
        }
        return totalAmount;


    }
}
