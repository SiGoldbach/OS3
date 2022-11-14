import java.util.Random;

public class Account implements Runnable {
    private String id;
    private int amount;

    public Account(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    synchronized public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return id + " account has: " + amount + " kr";
    }

    /**
     * Here there are 500
     */
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            int from = new Random().nextInt(BankSystem.accounts.size());
            int to = new Random().nextInt(BankSystem.accounts.size());
            if (from == to) {
                continue;
            }
            transaction(from, to, new Random().nextInt(50));


        }

    }

    public void transaction(int from, int to, int amount) {
        System.out.println("Transferring from: "+from+" to "+to);
        BankSystem.accounts.get(from).setAmount(this.amount - amount);
        BankSystem.accounts.get(to).setAmount(this.amount + amount);

    }

}
