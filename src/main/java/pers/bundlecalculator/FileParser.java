package pers.bundlecalculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.config.BundleConfig;
import pers.bundlecalculator.config.IBundleConfig;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.Order;
import pers.bundlecalculator.model.OrderItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private static final Logger logger = LogManager.getLogger(FileParser.class);

    public IBundleConfig parseBundleConfig(String bundleFilePath) {
        IBundleConfig config = new BundleConfig();
        try {
            BufferedReader br = new BufferedReader(new FileReader(bundleFilePath));
            CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',').withHeader().parse(br);
            for (CSVRecord record : parser) {
                logger.info("Parsing bundles: {} : {}", record.get("Format code"), record.get("Bundles"));
                String bundlesText = record.get("Bundles");
                String format = record.get("Format code");
                Pattern bundleWholePattern = Pattern.compile("[1-9]\\d*\\s*@\\s*[\\$]*(\\d+(?:\\.\\d+)?)");
                Matcher whole = bundleWholePattern.matcher(bundlesText);
                while (whole.find()) {
                    String[] bundleDetail = whole.group().replaceAll("[^\\d\\s.]", "").split("\\s+");
                    int quantity = Integer.parseInt(bundleDetail[0].trim());
                    float price = Float.parseFloat(bundleDetail[1]);
                    config.addBundle(format, new Bundle(quantity, price));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        logger.info("BundleConfig Loaded successfully!");
        return config;
    }

    public Order parseOrder(String orderFilePath) {
        Order order = new Order();
        try {
            BufferedReader br = new BufferedReader(new FileReader(orderFilePath));
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] tmp = line.split(" ");
                    if (tmp.length != 2) {
                        throw new IllegalArgumentException("Incorrect argument number: " + line);
                    }
                    String formatCode = tmp[1];
                    int quantity = Integer.parseInt(tmp[0]);
                    order.addItem(new OrderItem(quantity, formatCode));
                } catch (IllegalArgumentException ex) {
                    System.out.println(line + ": Illegal order format!");
                    logger.error(ex.getMessage());
                } finally {
                    continue;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        return order;
    }
}
