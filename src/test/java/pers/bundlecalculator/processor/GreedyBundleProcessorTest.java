package pers.bundlecalculator.processor;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreedyBundleProcessorTest {
    private static final GreedyBundleProcessor staticProcessor = new GreedyBundleProcessor();
    private static final List<Integer> bundleList = new ArrayList<>(Arrays.asList(3, 5, 9));

    @Test
    void processOrder_whenOrderQuantityLessThanSmallest() {
        Map<Integer, Integer> result = staticProcessor.process(2, bundleList);

        assertEquals(1, result.get(3));
        assertEquals(0, result.get(5));
        assertEquals(0, result.get(9));
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithNoReminder() {
        Map<Integer, Integer> result = staticProcessor.process(14, bundleList);

        assertEquals(0, result.get(3));
        assertEquals(1, result.get(5));
        assertEquals(1, result.get(9));
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithReminder1() {
        Map<Integer, Integer> result = staticProcessor.process(13, bundleList);

        assertEquals(2, result.get(3));
        assertEquals(0, result.get(5));
        assertEquals(1, result.get(9));
    }

    @Test
    void processOrder_whenOrderQuantityBreakDownWithReminder2() {
        Map<Integer, Integer> result = staticProcessor.process(4, bundleList);

        assertEquals(2, result.get(3));
        assertEquals(0, result.get(5));
        assertEquals(0, result.get(9));
    }
}
