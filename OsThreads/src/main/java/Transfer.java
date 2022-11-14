public class Transfer implements Runnable {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    private int change;

    public Transfer(Account account, int change) {

        this.account = account;
        this.change = change;
    }

    /**
     * Making a transfer this method will be called
     */
    @Override
    public void run() {
        account.setAmount((account.getAmount() + change));
    }

    public synchronized void deposit(int amount) {

    }
}
