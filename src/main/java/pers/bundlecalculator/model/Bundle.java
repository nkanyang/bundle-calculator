package pers.bundlecalculator.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Bundle implements Comparable {
    @Getter
    private final int quantity;
    @Getter
    private final float price;

    public Bundle(int quantity, double price) {
        this.quantity = quantity;
        this.price = (float)price;
    }

    @Override
    public int compareTo(Object o) {
        Bundle b = (Bundle) o;
        return Integer.compare(this.quantity, b.quantity);
    }
}