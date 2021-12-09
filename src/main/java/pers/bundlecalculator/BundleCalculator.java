package pers.bundlecalculator;

import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.processor.GreedyBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.util.HashMap;

public class BundleCalculator {
    private HashMap<String, IBundleProcessor> bundlesProcessors = new HashMap<>();
    public boolean addBundleProcessor(String formatCode, GreedyBundleProcessor processor){
        this.bundlesProcessors.put(formatCode, processor);
        return true;
    }
    public void processOrder(OrderItem orderItem){
        IBundleProcessor processor = this.bundlesProcessors.get(orderItem.getFormatCode());
        processor.processOrder(orderItem);
    }
}
