package pers.bundlecalculator.processor;

import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;

public interface IBundleProcessor {
    public void addBundle(Bundle bundle);
    public Output processOrder(OrderItem orderItem);
}
