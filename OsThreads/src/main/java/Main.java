import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        BankSystem.createExample();
        for (int i = 0; i < BankSystem.accounts.size(); i++) {
            executorService.execute(BankSystem.accounts.get(i));
        }
        Thread.sleep(5000);
        BankSystem.print();


        executorService.shutdown();
    }

    public static boolean breaking(int iterations, int max) {
        int k = 0;
        Random rand = new Random();
        Account aq = new Account("hej", 100);
        int actualBalance = aq.getAmount();
        for (int i = 0; i < iterations; i++) {
            k = rand.nextInt(max);
            actualBalance += k;
            executorService.execute(new Transfer(aq, k));


        }
        //Here the thread needs to sleep since the comparison for correctness needs to wait after all threads,
        //are finished
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }

        // System.out.println("Balance should be: " + actualBalance);
        //System.out.println("Balance is: " + aq.getAmount());


        return actualBalance == aq.getAmount();
    }

    public static int testBreaking(int iteration, int max, int outerIterations) {
        int count = outerIterations;
        int iterations = count;
        for (int i = 0; i < iterations; i++) {
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {

            }
            if (!breaking(iteration, max)) {
                count--;
                System.out.println("            FAILING");
            }


        }


        return count;
    }
}
