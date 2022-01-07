package pers.bundlecalculator.processor;

import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class DpBundleProcessor implements IBundleProcessor {

    @Override
    public Map<Integer, Integer> process(int quantity, List<Integer> bundles) {
        int smallest = bundles.stream()
                .min(Comparator.comparing(Integer::valueOf))
                .get();

        Map<Integer, Integer>[] dp = new Map[quantity + 1];
        for (int i = 0; i < quantity + 1; i++) {
            dp[i] = new HashMap<>();
            for (int bundleSize : bundles) {
                dp[i].put(bundleSize, 0);
            }
        }

        if (quantity <= smallest) {
            dp[quantity].put(smallest, 1);
            return dp[quantity];
        }

        int[] value = new int[quantity + 1];
        int i = 1;
        for (; i <= smallest; i++) {
            value[i] = 1;
            dp[i].put(smallest, 1);
        }
        for (; i < quantity + 1; i++) {
            value[i] = quantity + 1;
            for (int j = 0; j < bundles.size(); j++) {
                int bundleSize = bundles.get(j);
                if (i >= bundleSize && value[i] > value[i - bundleSize] + 1) {
                    dp[i] = new HashMap<>(dp[i - bundleSize]);
                    dp[i].put(bundleSize, dp[i].get(bundleSize) + 1);
                    value[i] = value[i - bundleSize] + 1;
                }
            }
        }
        return dp[quantity];
    }
}
