package pers.bundlecalculator.config;

import pers.bundlecalculator.model.Bundle;

import java.util.TreeSet;

public interface IBundleConfig {
    void addBundle(String formatCode, Bundle bundle);

    TreeSet<Bundle> getBundles(String formatCode);
}
