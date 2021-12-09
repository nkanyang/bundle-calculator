package pers.bundlecalculator.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pers.bundlecalculator.model.Bundle;
import pers.bundlecalculator.model.OrderItem;
import pers.bundlecalculator.model.Output;

public class GreedyProcessorTest {
    private GreedyBundleProcessor processor = new GreedyBundleProcessor();
    @BeforeEach
    void init(){
        this.processor.addBundle(new Bundle(3, 427.5));
        this.processor.addBundle(new Bundle(6, 810));
        this.processor.addBundle(new Bundle(9, 1147.5));
    }
    @Test
    void processProcessOrder_whenOrderHasNoReminder(){
        GreedyBundleProcessor processor = new GreedyBundleProcessor();
        processor.addBundle(new Bundle(3, 427.5));
        processor.addBundle(new Bundle(6, 810));
        processor.addBundle(new Bundle(9, 1147.5));
        Output result = processor.processOrder(new OrderItem(15, "Flac"));
        System.out.println(result);

    }
    @Test
    void processProcessOrder_whenOrderHasReminder(){
        GreedyBundleProcessor processor = new GreedyBundleProcessor();
        processor.addBundle(new Bundle(5, 450));
        processor.addBundle(new Bundle(10, 800));
        Output result = processor.processOrder(new OrderItem(10, "Image"));
        System.out.println(result);
    }

    @Test
    void processProcessOrder(){
        GreedyBundleProcessor processor = new GreedyBundleProcessor();
        processor.addBundle(new Bundle(3, 570));
        processor.addBundle(new Bundle(5, 900));
        processor.addBundle(new Bundle(9, 1530));
        Output result = processor.processOrder(new OrderItem(10, "VID"));
        System.out.println(result);
    }


}
