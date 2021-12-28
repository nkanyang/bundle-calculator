package pers.bundlecalculator.processor;

import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.FilledOrderChildItem;

import java.util.*;

@NoArgsConstructor
public class DpBundleProcessor implements IBundleProcessor {
    public static final Logger logger = LogManager.getLogger(DpBundleProcessor.class);

    @Override
    public FilledOrderItem processOrder(OrderItem orderItem, TreeSet<Bundle> bundleSet) {
        FilledOrderItem filledOrderItem = new FilledOrderItem(orderItem);
        ArrayList<Integer> combination = this.process(orderItem.getQuantity(), bundleSet);
        if (combination == null) {
            filledOrderItem.addItem(new FilledOrderChildItem(1, bundleSet.first()));
        } else {
            Iterator<Bundle> bundleIt = bundleSet.descendingIterator();
            int reminder = orderItem.getQuantity();
            while (bundleIt.hasNext()) {
                Bundle bundle = bundleIt.next();
                int count = Collections.frequency(combination, bundle.getQuantity());
                reminder -= count * bundle.getQuantity();
                if (reminder > 0 && !bundleIt.hasNext()) {
                    count++;
                }
                if (count > 0) {
                    filledOrderItem.addItem(new FilledOrderChildItem(count, bundle));
                }
            }
            logger.debug(Arrays.toString(combination.toArray()));
        }
        return filledOrderItem;
    }

    private ArrayList<Integer> process(int amount, TreeSet<Bundle> bundleSet) {
        int[] bundleArray = bundleSet.stream()
                .filter(Objects::nonNull)
                .mapToInt(i -> i.getQuantity())
                .toArray();

        int[] dp = new int[amount + 1];
        ArrayList<Integer>[] stacks = new ArrayList[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
            stacks[i] = null;
        }

        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < bundleArray.length; j++) {
                if (bundleArray[j] <= i) {
                    if (dp[i] > dp[i - bundleArray[j]] + 1) {
                        if (stacks[i - bundleArray[j]] != null) {
                            stacks[i] = new ArrayList<>(stacks[i - bundleArray[j]]);
                        } else {
                            stacks[i] = new ArrayList<>();
                        }
                        stacks[i].add(bundleArray[j]);
                        dp[i] = dp[i - bundleArray[j]] + 1;
                    }
                    dp[i] = Math.min(dp[i], dp[i - bundleArray[j]] + 1);
                }
            }
        }
        for (int i = amount; i > 0; i--) {
            if (stacks[i] != null) {
                return stacks[i];
            }
        }
        return null;
    }
}
