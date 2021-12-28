package pers.bundlecalculator;

import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    public void main_whenOrderFormatWrong() {
        System.out.println(System.getProperty("user.dir"));
        String[] args = new String[]{"src/test/resources/bundles.csv", "src/test/resources/order1.txt"};
        Application.main(args);
    }

    @Test
    public void main_whenOrderFormatRight() {
        System.out.println(System.getProperty("user.dir"));
        String[] args = new String[]{"src/test/resources/bundles.csv", "src/test/resources/order2.txt"};
        Application.main(args);
    }
}