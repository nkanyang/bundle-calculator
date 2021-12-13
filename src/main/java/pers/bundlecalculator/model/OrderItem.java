package pers.bundlecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OrderItem {
    @Getter
    private final int quantity;
    @Getter
    private final String formatCode;

    public String toString(){
        return this.quantity + " " + this.formatCode;
    }
}
