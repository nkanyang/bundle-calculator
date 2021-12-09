package pers.bundlecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OrderItem {
    @Getter
    private int quantity;
    @Getter
    private String formatCode;

    public String toString(){
        return this.quantity + " " + this.formatCode;
    }
}
