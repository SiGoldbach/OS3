import java.util.Random;
import java.util.concurrent.Semaphore;

public class Account implements Runnable {
    private final String id;
    //Personal semaphore
    private final Semaphore semaphore = new Semaphore(1);
    //Semaphore being borrowed
    private Account toTransferAccount;
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
        for (int i = 0; i < new Random().nextInt(10) + 5; i++) {
            int to = new Random().nextInt(BankSystem.accounts.size());
            toTransferAccount = BankSystem.accounts.get(to);
            if (this.id.equals(BankSystem.accounts.get(to).id)) {
                continue;
            }
            while (true) {
                try {
                    if (this.semaphore.tryAcquire()) {
                        if (toTransferAccount.semaphore.tryAcquire()) {
                            //System.out.println("Going to transact");
                            break;
                        } else {
                            this.semaphore.release();
                        }
                    } else {
                        //  System.out.println("Going again");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    System.out.println("Was busy");


                }


            }
            try {
                transaction(new Random().nextInt(50));
                //Releasing the semaphores after they have been used.
                toTransferAccount.semaphore.release();
                this.semaphore.release();
                //  System.out.println(semaphore.availablePermits());
                //  System.out.println("After transact");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

    }

    /**
     * This is the critical code. Where there must only be read and written to the same account on time concurrently
     *
     * @param amount Amount being transferred from one place to another
     */
    public void transaction(int amount) throws InterruptedException {
        // System.out.println("Transferring: " + amount + " from: to " + AccountToTransferTo);
        //Removing money from the account
        int currentVal = this.amount;
        //Thread.sleep(100);
        int readValue;
        //Reading the accounts value from the other account
        readValue = toTransferAccount.getAmount();
        try {
            Thread.sleep(100);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        //Writing the new value
        toTransferAccount.setAmount(readValue + amount);
        this.amount = currentVal - amount;


    }
}
