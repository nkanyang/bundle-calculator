package pers.bundlecalculator;

import org.junit.jupiter.api.Test;
import pers.bundlecalculator.config.IBundleConfig;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.FilledOrderItem;
import pers.bundlecalculator.model.Order;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.processor.IBundleProcessor;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BundleCalculatorTest {
    @Test
    public void processOrder_whenFormatSupported_thenOK() {
        IBundleProcessor processor = mock(IBundleProcessor.class);
        IBundleConfig config = mock(IBundleConfig.class);
        TreeSet<Bundle> bundleSet = new TreeSet<Bundle>();
        BundleCalculator calculator = new BundleCalculator(config, processor);

        OrderItem orderItem = new OrderItem(12, "Test");
        Order order = new Order();
        order.addItem(orderItem);
        FilledOrderItem filledOrderItem = new FilledOrderItem(orderItem);
        when(config.getBundles(anyString())).thenReturn(bundleSet);
        when(processor.processOrder(orderItem, bundleSet)).thenReturn(filledOrderItem);

        calculator.processOrder(order);

        verify(config, times(1)).getBundles(anyString());
        verify(processor, times(1)).processOrder(orderItem, bundleSet);
    }

    @Test
    public void processOrder_whenFormatNotSupported_thenThrow() {
        IBundleProcessor processor = mock(IBundleProcessor.class);
        IBundleConfig config = mock(IBundleConfig.class);
        TreeSet<Bundle> bundleSet = new TreeSet<Bundle>();
        BundleCalculator calculator = new BundleCalculator(config, processor);

        OrderItem orderItem = new OrderItem(12, "Test");
        Order order = new Order();
        order.addItem(orderItem);
        when(config.getBundles(anyString())).thenReturn(null);
        when(processor.processOrder(orderItem, bundleSet)).thenReturn(any());

        assertThrows(FormatNotSupportException.class,
                () -> calculator.processOrder(order));
        verify(config, times(1)).getBundles(anyString());
        verify(processor, times(0)).processOrder(orderItem, bundleSet);
    }
}
