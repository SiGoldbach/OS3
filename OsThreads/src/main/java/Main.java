import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executorService;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            System.out.println("Test: " + (i + 1));
            test();

        }

        BankSystem.createExample();

        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        System.exit(0);
    }


    public static void test() throws InterruptedException {
        BankSystem.createExample();
        executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            executorService.execute(BankSystem.accounts.get(i));

        }
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("In infinite loop");
            executorService.shutdown();
            if (executorService.isTerminated())
                break;
        }
        System.out.println("There should be: " + BankSystem.amountShouldBe + " There is " + BankSystem.getCurrent());
        if (BankSystem.amountShouldBe != BankSystem.getCurrent())
            System.out.println("Fail");

    }


}
