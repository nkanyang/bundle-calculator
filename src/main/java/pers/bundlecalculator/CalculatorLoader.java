package pers.bundlecalculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.processor.DpBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorLoader {
    private static final Logger logger = LogManager.getLogger(CalculatorLoader.class);

    public BundleCalculator loadCalculator(String bundleFile) {
        BundleCalculator calculator = new BundleCalculator();
        try {
            BufferedReader br = new BufferedReader(new FileReader(bundleFile));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
            for (CSVRecord record : parser) {
                IBundleProcessor processor = new DpBundleProcessor();
                logger.info("Parsing bundles: {} : {}", record.get("Format code"), record.get("Bundles"));
                String bundlesText = record.get("Bundles");
                Pattern bundleWholePattern = Pattern.compile("[1-9]\\d*\\s*@\\s*[\\$]*(\\d+(?:\\.\\d+)?)");
                Matcher whole = bundleWholePattern.matcher(bundlesText);
                while (whole.find()) {
                    Bundle bundle = this.parseBundle(whole.group());
                    processor.addBundle(bundle);
                }
                calculator.addBundleProcessor(record.get("Format code"), processor);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        logger.info("Bundles Loaded successfully!");
        return calculator;
    }

    public Bundle parseBundle(String singleBundle) {
        Pattern bundleSinglePattern = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher bundle = bundleSinglePattern.matcher(singleBundle);
        int count = 0;
        int quantity = 0;
        float price = 0;
        while (bundle.find()) {
            switch (count) {
                case 0:
                    quantity = Integer.parseInt(bundle.group());
                    break;
                case 1:
                    price = Float.parseFloat(bundle.group());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            count++;
        }
        return new Bundle(quantity, price);
    }
}
