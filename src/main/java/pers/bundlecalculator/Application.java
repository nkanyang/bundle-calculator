package pers.bundlecalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        validateArguments(args);
        CalculatorLoader loader = new CalculatorLoader();
        BundleCalculator calculator = loader.loadCalculator(args[0]);
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[1]));
            String line;
            Output result = null;
            while ((line = br.readLine()) != null) {
                try {
                    String[] tmp = line.split(" ");
                    if (tmp.length != 2) {
                        throw new IllegalArgumentException("Incorrect argument number: " + line);
                    }
                    String formatCode = tmp[1];
                    int quantity = Integer.parseInt(tmp[0]);
                    result = calculator.processOrder(new OrderItem(quantity, formatCode));
                    System.out.println(result);
                } catch (IllegalArgumentException ex) {
                    System.out.println(line + ": Parse Failed!");
                    logger.error(ex.getMessage());
                } finally {
                    continue;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
    }

    public static void showHelpInfo() {
        String helpInfo = "\n  Usage: \n    java -jar bundlecalcultor.jar <bundleFileName> <orderFileName>\n";
        System.out.println(helpInfo);
        System.exit(0);
    }

    public static void validateArguments(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong argument number!");
            showHelpInfo();
        }

        for (String fileName : args) {
            if (!new File(fileName).exists()) {
                System.out.println("File \"" + fileName + "\" does not exists!");
                showHelpInfo();
            }
        }
    }
}