import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            System.out.println("Test: " + i);
            test();

        }

        BankSystem.createExample();


        executorService.shutdown();
    }


    public static void test() throws InterruptedException {
        BankSystem.createExample();
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            executorService.execute(BankSystem.accounts.get(i));

        }
        Thread.sleep(10000);
        System.out.println("There should be: " + BankSystem.amountShouldBe + " There is " + BankSystem.getCurrent());
        if (BankSystem.amountShouldBe != BankSystem.getCurrent())
            System.out.println("Fail");

    }


}
