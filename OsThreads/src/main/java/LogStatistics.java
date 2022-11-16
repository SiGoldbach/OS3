public class LogStatistics {
    private int failedAttempt;
    private int transfers;

    private final int expectedTransfers;


    public LogStatistics(int failedAttempt, int transfers, int expectedTransfers) {
        this.failedAttempt = failedAttempt;
        this.transfers = transfers;
        this.expectedTransfers = expectedTransfers;
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
        return (double) transfers / (double) failedAttempt;
    }

    public void printStatus(String accountName) {
        System.out.println(accountName);
        System.out.println("    Fails: " + failedAttempt);
        System.out.println("    Success: " + transfers);
        System.out.println("    Expected: " + expectedTransfers);

    }
}
