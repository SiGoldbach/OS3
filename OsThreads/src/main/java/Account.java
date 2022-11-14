import java.util.Random;

public class Account implements Runnable {
    private final String id;
    private int amount;

    public Account(String id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
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
        for (int i = 0; i < new Random().nextInt(200) + 100; i++) {
            int to = new Random().nextInt(BankSystem.accounts.size());
            if (this.id.equals(BankSystem.accounts.get(to).id)) {
                continue;
            }
            try {
                transaction(to, new Random().nextInt(50));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

    /**
     * This is the critical code. Where there must only be read and written to the same account on time concurrently
     *
     * @param AccountToTransferTo The account getting the money
     * @param amount              Amount being transferred from one place to another
     */
    public void transaction(int AccountToTransferTo, int amount) throws InterruptedException {
        // System.out.println("Transferring: " + amount + " from: to " + AccountToTransferTo);
        this.amount = this.amount - amount;
        Thread.sleep(100);
        BankSystem.accounts.get(AccountToTransferTo).setAmount(BankSystem.accounts.get(AccountToTransferTo).getAmount() + amount);


    }
}
