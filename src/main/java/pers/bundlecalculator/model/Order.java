package pers.bundlecalculator.model;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;

@NoArgsConstructor
public class Order {
    private final ArrayList<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public Iterator<OrderItem> getIterator() {
        return this.items.listIterator();
    }
}
