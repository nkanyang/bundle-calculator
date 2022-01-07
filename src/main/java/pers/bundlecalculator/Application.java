package pers.bundlecalculator;

import pers.bundlecalculator.config.IBundleConfig;
import pers.bundlecalculator.model.Order;
import pers.bundlecalculator.processor.DpBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.io.File;

public class Application {
    public static void main(String[] args) {
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
        FileParser fileParser = new FileParser();
        IBundleConfig config = fileParser.parseBundleConfig(args[0]);
        IBundleProcessor processor = new DpBundleProcessor();
        BundleCalculator calculator = new BundleCalculator(config, processor);

        Order order = fileParser.parseOrder(args[1]);
        calculator.processOrder(order);
    }

    public static void showHelpInfo() {
        String helpInfo = "\n  Usage: \n    java -jar bundlecalcultor.jar <bundleFileName> <orderFileName>\n";
        System.out.println(helpInfo);
        System.exit(0);
    }
}