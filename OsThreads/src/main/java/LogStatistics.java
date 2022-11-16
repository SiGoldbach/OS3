public class LogStatistics {
    private int failedAttempt;

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public int getTransfers() {
        return transfers;
    }

    public int getExpectedTransfers() {
        return expectedTransfers;
    }

    private int transfers;

    private String accountName;


    private final int expectedTransfers;


    public LogStatistics(int failedAttempt, int transfers, int expectedTransfers, String accountName) {
        this.failedAttempt = failedAttempt;
        this.transfers = transfers;
        this.expectedTransfers = expectedTransfers;
        this.accountName = accountName;
    }

    public void addFail() {
        failedAttempt++;
    }

    public void addSuccess() {
        transfers++;

    }


    public boolean checksOut() {
        return transfers == expectedTransfers;
    }

    public double getRatio() {
        return (double) failedAttempt / (double) transfers;
    }

    public void printStatus(String accountName) {
        System.out.println(accountName);
        System.out.println("    Fails: " + failedAttempt);
        System.out.println("    Success: " + transfers);
        System.out.println("    Expected: " + expectedTransfers);

    }

    @Override
    public String toString() {
        return this.accountName + "\n" +
                "   Fails: " + failedAttempt + "\n" +
                "   Transfer" + transfers + "\n" +
                "   ExpectedTransfer: " + expectedTransfers + "\n" +
                "   Ratio: " + getRatio() + "\n\n";
    }
}
