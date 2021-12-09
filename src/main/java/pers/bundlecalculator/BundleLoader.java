package pers.bundlecalculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BundleLoader {
    public BundleCalculator loadCalculator(String bundleFile){
        BundleCalculator calculator = new BundleCalculator();
        try {
            BufferedReader br = new BufferedReader(new FileReader(bundleFile));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
            for(CSVRecord record : parser){
                calculator.addBundleProcessor(record.get("Format code"), record.get("Bundles"));
            }
        }catch (IOException e ){
            System.out.println(e.getMessage()); //add to log?
            System.exit(1);
        }
        return calculator;
    }
}
