package pers.bundlecalculator;

import pers.bundlecalculator.model.OrderItem;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class main {
    public static void main(String[] args){
        System.out.println("Hello World");
        System.out.println(System.getProperty("user.dir"));
        if(args.length != 1){
            System.out.println();
            showHelpInfo();
            System.exit(0);
        }
        File orderFile = new File(args[0]);
        if(!orderFile.exists()){
            System.out.println("File \"" + args[0] + "\" does not exists!");
            showHelpInfo();
            System.exit(0);
        }
        CalculatorLoader loader = new CalculatorLoader();
        BundleCalculator calculator = loader.loadCalculator();
        calculator.processOrder(new OrderItem(13, "IMG"));
    }

    public static void showHelpInfo() {
        String helpInfo = "Usage: java -jar bundlecalcultor.jar <orderFileName>\n"
                + "\n"
                + "options: \n"
                + "    -l, --log          Show log of the execution of the toy robot simulator\n";
        System.out.println(helpInfo);
    }

    public static void validateArguments() {

    }
}