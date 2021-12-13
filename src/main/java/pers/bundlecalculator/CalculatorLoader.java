package pers.bundlecalculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.processor.DpBundleProcessor;
import pers.bundlecalculator.processor.GreedyBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorLoader {
    Properties props = new Properties();
    public BundleCalculator loadCalculator(){
        this.loadConfig();
        String bundleFile = this.props.getProperty("bundleSubmission");
        if(bundleFile == null){
            //TODO:throw exceptions configuration not found
        }
        BundleCalculator calculator = new BundleCalculator();
        try {
            BufferedReader br = new BufferedReader(new FileReader(bundleFile));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
            for(CSVRecord record : parser){
                IBundleProcessor processor = new DpBundleProcessor();
                String bundlesText = record.get("Bundles");
                System.out.println(bundlesText);
                Pattern bundleWholePattern = Pattern.compile("[1-9]\\d*\\s*@\\s*[\\$]*(\\d+(?:\\.\\d+)?)");
                Pattern bundleSinglePattern = Pattern.compile("(\\d+(?:\\.\\d+)?)");
                Matcher whole = bundleWholePattern.matcher(bundlesText);
                while(whole.find()) {
                    String bundle = whole.group();
                    Matcher single = bundleSinglePattern.matcher(bundle);
                    int count = 0;
                    int quantity = 0;
                    float price = 0;
                    while(single.find()){
                        switch (count){
                            case 0: quantity = Integer.parseInt(single.group()); System.out.println("quantity:" + quantity); break;
                            case 1: price = Float.parseFloat(single.group()); System.out.println("price:" + price); break;
                            default: System.out.println("should throw something here");
                        }
                        count++;
                    }
                    processor.addBundle(new Bundle(quantity, price));
                }
                calculator.addBundleProcessor(record.get("Format code"), processor);
            }
        }catch (IOException e ){
            System.out.println(e.getMessage()); //add to log?
            System.exit(1);
        }
        return calculator;
    }
    private void loadConfig(){
        try {
            FileReader reader = new FileReader("src/main/resources/application.properties");
            this.props.load(reader);
            reader.close();
//            System.out.println(props.getProperty("bundleSubmission"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
