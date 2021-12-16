package pers.bundlecalculator;

import org.junit.jupiter.api.Test;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;
import pers.bundlecalculator.processor.DpBundleProcessor;
import pers.bundlecalculator.processor.IBundleProcessor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BundleCalculatorTest {
    @Test
    public void processOrder_whenFormatSupported_thenOK(){
        IBundleProcessor processor = mock(DpBundleProcessor.class);
        BundleCalculator calculator = new BundleCalculator();
        OrderItem orderItem = new OrderItem(13, "Mock");
        calculator.addBundleProcessor("Mock", processor);
        when(processor.processOrder(orderItem)).thenReturn(new Output(orderItem));

        calculator.processOrder(orderItem);

        verify(processor, times(1)).processOrder(orderItem);
    }

    @Test
    public void processOrder_whenFormatNotSupported_thenThrow(){
        BundleCalculator calculator = new BundleCalculator();
        OrderItem orderItem = new OrderItem(13, "Mock");

        assertThrows(FormatNotSupportException.class,
                () -> calculator.processOrder(orderItem));
    }
}
