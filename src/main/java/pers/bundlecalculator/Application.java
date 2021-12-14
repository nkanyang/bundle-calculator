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
    public static void main(String[] args){
//        System.out.println(System.getProperty("user.dir"));
        if(args.length != 1){
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
            Output result = null;
            while((line = br.readLine()) != null){
                try{
                    String[] tmp = line.split(" ");
                    if(tmp.length != 2){
                        throw new IllegalArgumentException("Incorrect argument number: " + line);
                    }
                    String formatCode = tmp[1];
                    int quantity = Integer.parseInt(tmp[0]);
                    result = calculator.processOrder(new OrderItem(quantity, formatCode));
                    System.out.println(result);
                }catch (IllegalArgumentException ex){
                    System.out.println(line + ": Parse Failed!");
                    logger.error(ex.getMessage());
                }
                finally {
                    continue;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
    }

    public static void showHelpInfo() {
        String helpInfo = "\n\n  Usage: java -jar bundlecalcultor.jar <orderFileName>\n";
        System.out.println(helpInfo);
    }
}