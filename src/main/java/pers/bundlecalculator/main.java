package pers.bundlecalculator;

import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;

public class main {
    public static void main(String[] args){
        System.out.println("Hello World");
        System.out.println(System.getProperty("user.dir"));
        BundleLoader loader = new BundleLoader();
        BundleCalculator calculator = loader.loadCalculator("sampledata/bundle.csv");
        calculator.processOrder(new OrderItem(13, "IMG"));
    }

    public static void showHelpInfo() { //todo: Update
        String helpInfo = "Usage: java -jar toyrobot.jar <fileName> [options]\n"
                + "\n"
                + "options: \n"
                + "    -l, --log          Show log of the execution of the toy robot simulator\n";
        System.out.println(helpInfo);
    }
}