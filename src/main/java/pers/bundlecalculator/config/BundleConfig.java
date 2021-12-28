package pers.bundlecalculator.config;

import lombok.NoArgsConstructor;
import pers.bundlecalculator.model.Bundle;

import java.util.HashMap;
import java.util.TreeSet;

@NoArgsConstructor
public class BundleConfig implements IBundleConfig {
    private final HashMap<String, TreeSet<Bundle>> bundleSet = new HashMap<>();

    public void addBundle(String formatCode, Bundle bundle) {
        TreeSet<Bundle> bundles = bundleSet.get(formatCode);
        if (bundles == null) {
            bundles = new TreeSet<>();
            bundleSet.put(formatCode, bundles);
        }
        bundles.add(bundle);
    }

    public TreeSet<Bundle> getBundles(String formatCode) {
        return bundleSet.get(formatCode);
    }
}
