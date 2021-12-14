package pers.bundlecalculator.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.model.OutputItem;

import java.util.*;

public class DpBundleProcessor implements IBundleProcessor {
    private static final Logger logger = LogManager.getLogger(DpBundleProcessor.class);
    private TreeSet<Bundle> bundles = new TreeSet<>();
    @Override
    public void addBundle(Bundle bundle) {
        this.bundles.add(bundle);
    }

    @Override
    public Output processOrder(OrderItem orderItem) {
        Output output = new Output(orderItem);
        ArrayList<Integer> combination = this.process(orderItem.getQuantity());
        if(combination == null){
            output.addItem(new OutputItem(1, bundles.first()));
        }
        else{
            Iterator<Bundle> bundleIt = this.bundles.descendingIterator();
            int reminder = orderItem.getQuantity();
            while (bundleIt.hasNext()){
                Bundle bundle = bundleIt.next();
                int count = Collections.frequency(combination, bundle.getQuantity());
                reminder -= count * bundle.getQuantity();
                if(reminder > 0 && !bundleIt.hasNext()){
                    count ++;
                }
                if(count > 0){
                    output.addItem(new OutputItem(count, bundle));
                }
            }
            logger.debug(Arrays.toString(combination.toArray()));
        }
        return output;
    }

    private ArrayList<Integer> process(int amount) {
        int[] bundleArray = this.bundles.stream()
                .filter(Objects::nonNull)
                .mapToInt(i -> i.getQuantity())
                .toArray();

        int[] dp = new int[amount + 1];
        ArrayList<Integer>[] stacks = new ArrayList[amount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
            stacks[i] = null;
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < bundleArray.length; j++) {
                if (bundleArray[j] <= i) {
                    if(dp[i] > dp[i - bundleArray[j]] + 1){
                        if(dp[i] == amount + 1 && stacks[i - bundleArray[j]] != null ){
                            stacks[i] = new ArrayList<>(stacks[i - bundleArray[j]]);
                        }
                        else {
                            stacks[i] = new ArrayList<>();
                        }
                        stacks[i].add(bundleArray[j]);
                        dp[i] = dp[i - bundleArray[j]] + 1;
                    }
                    dp[i] = Math.min(dp[i], dp[i - bundleArray[j]] + 1);
                }
            }
        }
        for( int i = amount; i > 0; i--) {
            if(stacks[i] != null){
                return stacks[i];
            }
        }
        return null;
    }
}
