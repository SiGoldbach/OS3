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
        for (int i = 0; i < new Random().nextInt(500); i++) {
            int to = new Random().nextInt(BankSystem.accounts.size());
            if (this.id.equals(BankSystem.accounts.get(to).id)) {
                continue;
            }
            transaction(to, new Random().nextInt(50));


        }

    }

    /**
     * This is the critical code which needs to be only used the correct time
     *
     * @param
     * @param to
     * @param amount
     */
    public void transaction(int to, int amount) {
        System.out.println("Transferring: " + amount + " from: to " + to);
        this.amount = this.amount - amount;
        BankSystem.accounts.get(to).setAmount(this.amount + amount);

    }

}
