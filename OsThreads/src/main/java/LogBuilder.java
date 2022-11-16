import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * This class is a little synchronisation problem too since it needs to get a log from each
 * And it obviously needs a semaphore so only one log at a time gets added to the list.
 */
public class LogBuilder {
    public final Semaphore logSemaphore = new Semaphore(1);
    String logName;

    File log;
    List<LogStatistics> logs;

    private final int expectedLogs;

    //This constructor will create a file in the same
    public LogBuilder(String logName, int expectedLogs) {
        logs = new ArrayList<>();
        this.expectedLogs = expectedLogs;
        this.logName = logName + ".txt";

    }

    //Here a log gets added this code is critical since only one log should be added at one index.
    public void addLog(LogStatistics log) {
        logs.add(log);


    }

    public void buildLog() {
        if (logs.size() != expectedLogs) {
            System.out.println("Logs where not sent correctly");
        } else {
            System.out.println("Success building log");
        }
        log = new File(logName);
        System.out.println(log.getAbsolutePath());
        try {
            if (log.createNewFile()) {
                System.out.println("Creating file as expected ");


            } else {
                System.out.println("The is already a file at this location: " + log.getAbsolutePath());
                System.out.println("Press enter ok to continue: ");
                if (!new Scanner(System.in).nextLine().equals("ok"))
                    System.exit(0);


            }
            try {
                System.out.println("Trying to write ");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(log));
                bufferedWriter.write(logger());
                for (LogStatistics logStatistics : logs) {
                    bufferedWriter.write(logStatistics.toString());
                }
                bufferedWriter.close();

            } catch (IOException e) {
                System.out.println("Cant write to file");
                e.printStackTrace();
            }


        } catch (IOException e) {
            System.out.println("Cant create file");
            e.printStackTrace();
        }


    }

    //Little comment when building a single log one
    private String logger() {
        int transfer = 0;
        int failedTries = 0;
        int expectedTransfer = 0;
        int expectedMoneyCirculation = BankSystem.amountShouldBe;
        for (LogStatistics logStatistics : logs) {
            transfer += logStatistics.getTransfers();
            failedTries += logStatistics.getFailedAttempt();
            expectedTransfer += logStatistics.getExpectedTransfers();

        }


        return "Test with: " + BankSystem.accounts.size() + " Accounts/threads\n" +
                "With " + BankSystem.transferAmount + " Transfer pr. Account \n" +
                "Amount of transfer is: " + transfer + " It should be:" + expectedTransfer + "\n" +
                "Failed tries is: " + failedTries + "\n" +
                "the ratio of fails to success is: " + ((double) failedTries / (double) transfer) + " Failures pr success.\n" +
                "The amount money in circulation is: " + BankSystem.getCurrent() + " The expected is: " + expectedMoneyCirculation + "\n";

    }
}
