package pers.bundlecalculator;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.config.IBundleConfig;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderChildItem;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.Order;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BundleCalculator {
    private static final Logger logger = LogManager.getLogger(BundleCalculator.class);
    private final IBundleConfig config;
    private final IBundleProcessor processor;

    public void processOrder(Order order) throws FormatNotSupportException {
        order.getItems().forEach(item -> {
            FilledOrderItem filledOrderItem = new FilledOrderItem(item);
            logger.info("Processing order: Format code = {}, quantity = {} ", item.getFormatCode(), item.getQuantity());

            Map<Integer, Bundle> bundleSet = this.config.getBundles(item.getFormatCode());
            if (bundleSet == null) {
                throw new FormatNotSupportException(item.getFormatCode());
            }

            Map<Integer, Integer> result = this.processor.process(item.getQuantity(),
                    bundleSet.keySet().stream().collect(Collectors.toList()));
            logger.debug("Result of processor: {}", result.toString());

            result.entrySet().stream()
                    .filter(e -> e.getValue() > 0)
                    .sorted((b1, b2) -> -Integer.compare(b1.getKey(), b2.getKey()))
                    .forEach(e -> filledOrderItem.addItem(new FilledOrderChildItem(e.getValue(), bundleSet.get(e.getKey()))));

            System.out.println(filledOrderItem);
        });
    }
}
