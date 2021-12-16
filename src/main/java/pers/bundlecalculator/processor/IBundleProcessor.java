package pers.bundlecalculator.processor;

import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;

public interface IBundleProcessor {
    void addBundle(Bundle bundle);

    Output processOrder(OrderItem orderItem);
}
