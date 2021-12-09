package pers.bundlecalculator.model;

import lombok.Getter;

public class OutputItem {
    private int quantity;
    private Bundle bundle;
    @Getter
    private float totalPrice;

    public OutputItem(int quantity, Bundle bundle){
        this.quantity = quantity;
        this.bundle = bundle;
        this.totalPrice = quantity * bundle.getPrice();
    }

    public String toString(){
        return String.format("%d x %d $%.2f", this.quantity, bundle.getQuantity(), this.totalPrice);
    }
}
