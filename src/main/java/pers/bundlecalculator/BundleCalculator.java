package pers.bundlecalculator;

import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.model.OutputItem;
import pers.bundlecalculator.processor.GreedyBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.util.HashMap;

public class BundleCalculator {
    private HashMap<String, IBundleProcessor> bundlesProcessors = new HashMap<>();

    public boolean addBundleProcessor(String formatCode, IBundleProcessor processor){
        this.bundlesProcessors.put(formatCode, processor);
        return true;
    }
    public void processOrder(OrderItem orderItem) throws FormatNotSupportException{
        IBundleProcessor processor = this.bundlesProcessors.get(orderItem.getFormatCode());
        if(processor == null){
            throw new FormatNotSupportException("Format " + orderItem.getFormatCode() + " not supported!");
        }
        Output output = processor.processOrder(orderItem);
        System.out.println(output);
    }
}
