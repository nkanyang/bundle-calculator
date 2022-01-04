package pers.bundlecalculator.config;

import pers.bundlecalculator.model.Bundle;

import java.util.Map;

public interface IBundleConfig {
    void addBundle(String formatCode, Bundle bundle);

    Map getBundles(String formatCode);
}
