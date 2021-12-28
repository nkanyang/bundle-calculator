package pers.bundlecalculator;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.config.IBundleConfig;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.Order;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.util.Iterator;
import java.util.TreeSet;

@AllArgsConstructor
public class BundleCalculator {
    private static final Logger logger = LogManager.getLogger(BundleCalculator.class);
    private IBundleConfig config;
    private IBundleProcessor processor;

    public void processOrder(Order order) throws FormatNotSupportException {
        Iterator<OrderItem> it = order.getIterator();
        while(it.hasNext()){
            OrderItem orderItem = it.next();
            logger.info("Processing order: Format code = {}, quantity = {} ", orderItem.getFormatCode(), orderItem.getQuantity());
            TreeSet<Bundle> bundleSet = this.config.getBundles(orderItem.getFormatCode());
            if (bundleSet == null) {
                throw new FormatNotSupportException(orderItem.getFormatCode());
            }
            FilledOrderItem result = this.processor.processOrder(orderItem, bundleSet);
            System.out.println(result);
        }
    }
}
