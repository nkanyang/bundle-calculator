package pers.bundlecalculator.model;

import lombok.Getter;

public class FilledOrderChildItem {
    private final int quantity;
    private final Bundle bundle;
    @Getter
    private final float totalPrice;

    public FilledOrderChildItem(int quantity, Bundle bundle) {
        this.quantity = quantity;
        this.bundle = bundle;
        this.totalPrice = quantity * bundle.getPrice();
    }

    public String toString() {
        return String.format("%d x %d $%.2f", this.quantity, bundle.getQuantity(), this.totalPrice);
    }
}
