package pers.bundlecalculator;

import org.junit.jupiter.api.Test;
import pers.bundlecalculator.config.IBundleConfig;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.Order;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.processor.IBundleProcessor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BundleCalculatorTest {
    @Test
    public void processOrder_whenFormatSupported_thenOK() {
        IBundleProcessor processor = mock(IBundleProcessor.class);
        IBundleConfig config = mock(IBundleConfig.class);
        BundleCalculator calculator = new BundleCalculator(config, processor);

        int orderQuantity = 12;
        OrderItem orderItem = new OrderItem(orderQuantity, "Test");
        Order order = new Order();
        order.addItem(orderItem);
        when(config.getBundles("Test")).thenReturn(anyMap());
        when(processor.process(orderQuantity, anyList())).thenReturn(anyMap());

        calculator.processOrder(order);

        verify(config, times(1)).getBundles(anyString());
        verify(processor, times(1)).process(anyInt(), anyList());
    }

    @Test
    public void processOrder_whenFormatNotSupported_thenThrow() {
        IBundleProcessor processor = mock(IBundleProcessor.class);
        IBundleConfig config = mock(IBundleConfig.class);
        BundleCalculator calculator = new BundleCalculator(config, processor);

        OrderItem orderItem = new OrderItem(12, "Test");
        Order order = new Order();
        order.addItem(orderItem);
        when(config.getBundles("Test")).thenReturn(null);
        when(processor.process(anyInt(), anyList())).thenReturn(any());

        assertThrows(FormatNotSupportException.class,
                () -> calculator.processOrder(order));
        verify(config, times(1)).getBundles(anyString());
        verify(processor, times(0)).process(anyInt(), anyList());
    }
}
