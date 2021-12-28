package pers.bundlecalculator.config;

import pers.bundlecalculator.model.Bundle;

import java.util.TreeSet;

public interface IBundleConfig {
    public void addBundle(String formatCode, Bundle bundle);
    public TreeSet<Bundle> getBundles(String formatCode);
}
