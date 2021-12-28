package pers.bundlecalculator.model;

import java.util.ArrayList;

public class FilledOrderItem {
    private final OrderItem orderItem;
    private float totalPrice = 0;
    private final ArrayList<FilledOrderChildItem> filledOrderChildItems = new ArrayList<>();

    public FilledOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public void addItem(FilledOrderChildItem item) {
        this.filledOrderChildItems.add(item);
        this.totalPrice += item.getTotalPrice();
    }

    public String toString() {
        String output = String.format("%s $%.2f", this.orderItem.toString(), this.totalPrice);
        for (FilledOrderChildItem item : filledOrderChildItems) {
            output = output + "\n  " + item.toString();
        }
        return output;
    }
}
