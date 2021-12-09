package pers.bundlecalculator.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;

public class DpBundleProcessorTest {

    @Test
    void testCoinChange(){
        DpBundleProcessor processor = new DpBundleProcessor();
        int[] coins = {3, 5, 9};
//        int result = processor.process(coins, 4);
//        System.out.println("number：" + result);
    }
    @Test
    void testProcess(){
        DpBundleProcessor processor = new DpBundleProcessor();
        processor.addBundle(new Bundle(9, 10));
        processor.addBundle(new Bundle(5, 60));
        processor.addBundle(new Bundle(3, 30));

        processor.processOrder(new OrderItem(13, "Img"));
//        System.out.println("number：" + result);
    }
}
