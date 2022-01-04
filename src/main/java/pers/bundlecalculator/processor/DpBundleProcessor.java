package pers.bundlecalculator.processor;

import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderChildItem;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.OrderItem;

import java.util.*;

@NoArgsConstructor
public class DpBundleProcessor implements IBundleProcessor {
    public static final Logger logger = LogManager.getLogger(DpBundleProcessor.class);

    @Override
    public Map<Integer, Integer> process(int quantity, List<Integer> bundles) {
        Map<Integer, Integer> result = new HashMap<>();
        ArrayList<Integer> combination = this.processDp(quantity, bundles);

        return result;
    }


//    @Override
//    public FilledOrderItem processOrder(OrderItem orderItem, TreeSet<Bundle> bundleSet) {
//        FilledOrderItem filledOrderItem = new FilledOrderItem(orderItem);
//
//        if (combination == null) {
//            filledOrderItem.addItem(new FilledOrderChildItem(1, bundleSet.first()));
//        } else {
//            Iterator<Bundle> bundleIt = bundleSet.descendingIterator();
//            int reminder = orderItem.getQuantity();
//            while (bundleIt.hasNext()) {
//                Bundle bundle = bundleIt.next();
//                int count = Collections.frequency(combination, bundle.getQuantity());
//                reminder -= count * bundle.getQuantity();
//                if (reminder > 0 && !bundleIt.hasNext()) {
//                    count++;
//                }
//                if (count > 0) {
//                    filledOrderItem.addItem(new FilledOrderChildItem(count, bundle));
//                }
//            }
//            logger.debug(Arrays.toString(combination.toArray()));
//        }
//        return filledOrderItem;
//    }

    private ArrayList<Integer> processDp(int amount, List<Integer> bundleSet) {
        int[] bundles = bundleSet.stream()
                .filter(Objects::nonNull)
                .mapToInt(i -> i)
                .toArray();
        int[] dp = new int[amount + 1];
        ArrayList<Integer>[] combination = new ArrayList[amount + 1];
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = amount + 1;
            for (int j = 0; j < bundles.length; j++) {
                if (i >= bundles[j] && dp[i] > dp[i - bundles[j]] + 1) {
                    combination[i] = combination[i - bundles[j]] != null
                            ? new ArrayList<>(combination[i - bundles[j]])
                            : new ArrayList<>();
                    combination[i].add(bundles[j]);
                    dp[i] = dp[i - bundles[j]] + 1;
                }
            }
        }
        for (int i = amount; i > 0; i--) {
            if (combination[i] != null) {
                return combination[i];
            }
        }
        return null;
    }


}
