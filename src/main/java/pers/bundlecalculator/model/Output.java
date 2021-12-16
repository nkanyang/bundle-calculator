package pers.bundlecalculator.model;

import java.util.ArrayList;

public class Output {
    private final OrderItem orderItem;
    private float totalPrice = 0;
    private final ArrayList<OutputItem> outputItems = new ArrayList<>();

    public Output(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void addItem(OutputItem item) {
        this.outputItems.add(item);
        this.totalPrice += item.getTotalPrice();
    }

    public String toString() {
        String output = String.format("%s $%.2f", this.orderItem.toString(), this.totalPrice);
        for (OutputItem item : outputItems) {
            output = output + "\n  " + item.toString();
        }
        return output;
    }
}
