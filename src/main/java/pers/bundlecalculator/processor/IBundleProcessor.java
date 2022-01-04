package pers.bundlecalculator.processor;

import java.util.List;
import java.util.Map;

public interface IBundleProcessor {

    Map<Integer, Integer> process(int quantity, List<Integer> bundles);
}

