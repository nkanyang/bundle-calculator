package pers.bundlecalculator;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    public void testApp(){
        String[] args = new String[]{"sampledata/order.txt"};
        Application.main(args);
    }
}
