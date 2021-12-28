package pers.bundlecalculator.processor;

import lombok.NoArgsConstructor;
import pers.bundlecalculator.model.*;

import java.util.Iterator;
import java.util.TreeSet;

@NoArgsConstructor
public class GreedyBundleProcessor implements IBundleProcessor {

    @Override
    public FilledOrderItem processOrder(OrderItem orderItem, TreeSet<Bundle> bundleSet) {
        FilledOrderItem filledOrderItem = new FilledOrderItem(orderItem);
        Iterator<Bundle> bundleIt = bundleSet.descendingIterator();
        int reminder = orderItem.getQuantity();
        while (bundleIt.hasNext()) {
            Bundle bundle = bundleIt.next();
            int count = reminder / bundle.getQuantity();
            reminder = reminder % bundle.getQuantity();
            if (reminder > 0 && !bundleIt.hasNext()) {
                count++;
            }
            if (count > 0) {
                filledOrderItem.addItem(new FilledOrderChildItem(count, bundle));
            }
        }
        return filledOrderItem;
    }
}
