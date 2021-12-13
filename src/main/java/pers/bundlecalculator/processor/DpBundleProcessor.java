package pers.bundlecalculator.processor;

import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.model.OutputItem;

import java.util.*;

public class DpBundleProcessor implements IBundleProcessor {
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
            System.out.println(Arrays.toString(combination.toArray()));
        }
        System.out.println(output);
        return output;
    }

    private ArrayList<Integer> process(int amount) {
        int[] buddleArray = this.bundles.stream()
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
            for (int j = 0; j < buddleArray.length; j++) {
                if (buddleArray[j] <= i) {
                    if(dp[i] > dp[i - buddleArray[j]] + 1){
                        if(dp[i] == amount + 1 && stacks[i - buddleArray[j]] != null ){
                            stacks[i] = new ArrayList<>(stacks[i - buddleArray[j]]);
                        }
                        else {
                            stacks[i] = new ArrayList<>();
                        }
                        stacks[i].add(buddleArray[j]);
                        dp[i] = dp[i - buddleArray[j]] + 1;
                    }
                    dp[i] = Math.min(dp[i], dp[i - buddleArray[j]] + 1);
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
