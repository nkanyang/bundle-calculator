package pers.bundlecalculator.processor;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class GreedyBundleProcessor implements IBundleProcessor {
    @Override
    public Map<Integer, Integer> process(int quantity, List<Integer> bundles) {
        Map<Integer, Integer> result = new HashMap<>();
        int reminder = quantity;

        List<Integer> descendingBundles = bundles.stream()
                .sorted((b1, b2) -> -Integer.compare(b1, b2))
                .collect(Collectors.toList());

        for (int bundleSize : descendingBundles) {
            int count = reminder / bundleSize;
            result.put(bundleSize, count);
            reminder = reminder % bundleSize;
        }

        if (reminder > 0) {
            int smallest = descendingBundles.get(descendingBundles.size() - 1);
            result.put(smallest, result.get(smallest) + 1);
        }

        return result;
    }
}
