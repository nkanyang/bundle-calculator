package pers.bundlecalculator.config;

import lombok.NoArgsConstructor;
import pers.bundlecalculator.model.Bundle;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class BundleConfig implements IBundleConfig {
    private final Map<String, Map<Integer, Bundle>> bundleSet = new HashMap<>();

    public void addBundle(String formatCode, Bundle bundle) {
        Map bundles = bundleSet.get(formatCode);
        if (bundles == null) {
            bundles = new HashMap<>();
            bundleSet.put(formatCode, bundles);
        }
        bundles.put(bundle.getQuantity(), bundle);
    }

    public Map<Integer, Bundle> getBundles(String formatCode) {
        return bundleSet.get(formatCode);
    }
}
