package pers.bundlecalculator.processor;

import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.model.OutputItem;

import java.util.*;

public class GreedyBundleProcessor implements IBundleProcessor {
    private TreeSet<Bundle> bundles = new TreeSet<>();
    public GreedyBundleProcessor(){

    }
    public void addBundle(Bundle bundle){
        this.bundles.add(bundle);
    }

    public Output processOrder(OrderItem orderItem){
        Output output = new Output(orderItem);
        Iterator<Bundle> bundleIt = this.bundles.descendingIterator();
        int reminder = orderItem.getQuantity();
        while (bundleIt.hasNext()){
            Bundle bundle = bundleIt.next();
            int count = reminder / bundle.getQuantity();
            reminder = reminder % bundle.getQuantity();
            if(reminder > 0 && !bundleIt.hasNext()){
                count ++;
            }
            if(count > 0){
                output.addItem(new OutputItem(count, bundle));
            }
        }
        return output;
    }
}
