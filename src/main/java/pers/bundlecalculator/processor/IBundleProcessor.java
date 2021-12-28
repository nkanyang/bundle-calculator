package pers.bundlecalculator.processor;

import pers.bundlecalculator.config.BundleConfig;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.OrderItem;

import java.util.TreeSet;

public interface IBundleProcessor {
//    void addBundle(Bundle bundle);

//    FilledOrderItem processOrder(OrderItem orderItem);
    FilledOrderItem processOrder(OrderItem orderItem, TreeSet<Bundle> bundleSet);

}
