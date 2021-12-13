package pers.bundlecalculator;

import pers.bundlecalculator.model.OrderItem;

import java.io.BufferedReader;
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
            System.exit(1);
        }
        CalculatorLoader loader = new CalculatorLoader();
        BundleCalculator calculator = loader.loadCalculator();

        try{
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line;
            while((line = br.readLine()) != null){
                try{
                    String[] tmp = line.split(" ");
                    if(tmp.length != 2){
                        throw new IllegalArgumentException("Incorrect argument number: " + line);
                    }
                    String formatCode = tmp[1];
                    int quantity = Integer.parseInt(tmp[0]);
                    calculator.processOrder(new OrderItem(quantity, formatCode));
                }catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }
                finally {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void showHelpInfo() {
        String helpInfo = "\nUsage: java -jar bundlecalcultor.jar <orderFileName>\n";
        System.out.println(helpInfo);
    }
}