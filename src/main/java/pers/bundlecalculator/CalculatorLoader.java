package pers.bundlecalculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pers.bundlecalculator.exception.MissingConfigurationException;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.processor.DpBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorLoader {
    Properties props = new Properties();
    public BundleCalculator loadCalculator(){
        BundleCalculator calculator = new BundleCalculator();
        try {
            FileReader reader = new FileReader("src/main/resources/application.properties");
            this.props.load(reader);
            reader.close();

            String bundleFile = this.props.getProperty("bundleSubmission");
            if(bundleFile == null){
                throw new MissingConfigurationException("bundleSubmission");
            }

            BufferedReader br = new BufferedReader(new FileReader(bundleFile));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
            for(CSVRecord record : parser){
                IBundleProcessor processor = new DpBundleProcessor();
                System.out.println(record.get("Format code"));
                String bundlesText = record.get("Bundles");
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
                            case 0: quantity = Integer.parseInt(single.group()); break;
                            case 1: price = Float.parseFloat(single.group()); break;
                            default: throw new IllegalArgumentException();
                        }
                        count++;
                    }
                    processor.addBundle(new Bundle(quantity, price));
                }
                calculator.addBundleProcessor(record.get("Format code"), processor);
            }
        }catch (Exception e ){
            System.out.println(e.getMessage()); //add to log?
            System.exit(1);
        }
        System.out.println("Bundles Loaded successfully!");
        return calculator;
    }
}
