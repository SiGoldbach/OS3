import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executorService;

    public static void main(String[] args) {
        Main.stressLog();
        //Main.test(100, 50, 1000, "exp");

    }
    //This takes around 3 min with i5 gen 8
    public static void stressLog(){
        test(1000,1000,5000,"StressTestLog");

    }


    public static void test(int accountAmount, int transfers, int amount, String logName) {
        BankSystem.createExample(accountAmount, transfers, amount, logName);
        executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            executorService.execute(BankSystem.accounts.get(i));

        }
        while (true) {
            //System.out.println("In infinite loop");
            executorService.shutdown();
            if (executorService.isTerminated())
                break;
        }
        //System.out.println("There should be: " + BankSystem.amountShouldBe + " There is " + BankSystem.getCurrent());
        if (BankSystem.amountShouldBe != BankSystem.getCurrent())
            System.out.println("Fail");
        BankSystem.logBuilder.buildLog();

    }


}
