package pers.bundlecalculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.util.HashMap;

public class BundleCalculator {
    private static final Logger logger = LogManager.getLogger(BundleCalculator.class);
    private final HashMap<String, IBundleProcessor> bundlesProcessors = new HashMap<>();

    public void addBundleProcessor(String formatCode, IBundleProcessor processor) {
        this.bundlesProcessors.put(formatCode, processor);
    }

    public Output processOrder(OrderItem orderItem) throws FormatNotSupportException {
        logger.info("Processing order: Format code = {}, quantity = {} ", orderItem.getFormatCode(), orderItem.getQuantity());
        IBundleProcessor processor = this.bundlesProcessors.get(orderItem.getFormatCode());
        if (processor == null) {
            throw new FormatNotSupportException(orderItem.getFormatCode());
        }
        return processor.processOrder(orderItem);
    }
}
