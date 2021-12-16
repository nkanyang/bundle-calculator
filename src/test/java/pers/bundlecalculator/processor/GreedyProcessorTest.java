package pers.bundlecalculator.processor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.model.OutputItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreedyProcessorTest {
    private static final GreedyBundleProcessor staticProcessor = new GreedyBundleProcessor();

    @BeforeAll
    public static void initProcessor() {
        staticProcessor.addBundle(new Bundle(3, 570));
        staticProcessor.addBundle(new Bundle(5, 900));
        staticProcessor.addBundle(new Bundle(9, 1530));
    }

    @Test
    void processOrder_whenOrderQuantityLessThanSmallest() {
        OrderItem order = new OrderItem(2, "Test");
        Output expected = new Output(order);
        expected.addItem(new OutputItem(1, new Bundle(3, 570)));

        Output result = staticProcessor.processOrder(order);

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithNoReminder() {
        OrderItem order = new OrderItem(14, "Test");
        Output expected = new Output(order);
        expected.addItem(new OutputItem(1, new Bundle(9, 1530)));
        expected.addItem(new OutputItem(1, new Bundle(5, 900)));

        Output result = staticProcessor.processOrder(order);

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithReminder1() {
        OrderItem order = new OrderItem(13, "Test");
        Output expected = new Output(order);
        expected.addItem(new OutputItem(1, new Bundle(9, 1530)));
        expected.addItem(new OutputItem(2, new Bundle(3, 570)));

        Output result = staticProcessor.processOrder(order);

        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithReminder2() {
        OrderItem order = new OrderItem(4, "Test");
        Output expected = new Output(order);
        expected.addItem(new OutputItem(2, new Bundle(3, 570)));

        Output result = staticProcessor.processOrder(order);

        assertEquals(expected.toString(), result.toString());
    }
}
