import org.junit.jupiter.api.Test;

public class TestingCases {
    //This is the test used for the experiment

    @Test
    void experiment() throws InterruptedException {
        Main.test(100,50,1000,"exp");

    }
    //This took 3 min and 4 sec on my pc with i5 gen 8 core.
    @Test
    void stress() throws InterruptedException{
        Main.test(1000,1000,1000,"Stress");
    }
}
