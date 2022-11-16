import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        this.logName = logName;

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
        log = new File(logName + ".txt");
        System.out.println(log.getAbsolutePath());
        try {
            if (log.createNewFile()) {
                System.out.println("Creating file as expected ");
            } else {
                String tempLogName;
                do {
                    tempLogName = new Random().nextInt(100000) + logName;
                    System.out.println("Making a new log instead of deleting or replacing anything on a someones pc");
                    log.renameTo(new File(tempLogName));
                } while (log.createNewFile());

            }
            try {
                System.out.println("Trying to write ");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(log));
                bufferedWriter.write("hat");
                bufferedWriter.flush();
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

    private String logger() {


        return "Fun stuff has been written to the file ";

    }
}
