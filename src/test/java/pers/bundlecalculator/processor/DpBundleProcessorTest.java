package pers.bundlecalculator.processor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderChildItem;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.OrderItem;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DpBundleProcessorTest {
    private static final DpBundleProcessor staticProcessor = new DpBundleProcessor();
    private static final TreeSet<Bundle> bundleSet = new TreeSet<>();

    @BeforeAll
    public static void initProcessor() {
        bundleSet.add(new Bundle(3, 570));
        bundleSet.add(new Bundle(5, 900));
        bundleSet.add(new Bundle(9, 1530));
    }

    @Test
    void processOrder_whenOrderQuantityLessThanSmallest() {
        OrderItem order = new OrderItem(2, "Test");
        FilledOrderItem expected = new FilledOrderItem(order);
        expected.addItem(new FilledOrderChildItem(1, new Bundle(3, 570)));

        FilledOrderItem result = staticProcessor.processOrder(order, bundleSet);

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithNoReminder() {
        OrderItem order = new OrderItem(13, "Test");
        FilledOrderItem expected = new FilledOrderItem(order);
        expected.addItem(new FilledOrderChildItem(2, new Bundle(5, 900)));
        expected.addItem(new FilledOrderChildItem(1, new Bundle(3, 570)));

        FilledOrderItem result = staticProcessor.processOrder(order, bundleSet);

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithReminder() {
        OrderItem order = new OrderItem(4, "Test");
        FilledOrderItem expected = new FilledOrderItem(order);
        expected.addItem(new FilledOrderChildItem(2, new Bundle(3, 570)));

        FilledOrderItem result = staticProcessor.processOrder(order, bundleSet);

        assertEquals(expected.toString(), result.toString());
    }
}
