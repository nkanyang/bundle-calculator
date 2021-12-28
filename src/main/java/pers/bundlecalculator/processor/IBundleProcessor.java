package pers.bundlecalculator.processor;

import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.OrderItem;

import java.util.TreeSet;

public interface IBundleProcessor {

    FilledOrderItem processOrder(OrderItem orderItem, TreeSet<Bundle> bundleSet);

}
