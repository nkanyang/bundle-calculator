package pers.bundlecalculator;

import org.junit.jupiter.api.Test;
import pers.bundlecalculator.exception.FormatNotSupportException;
import pers.bundlecalculator.model.Bundle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorLoaderTest {
    private CalculatorLoader loader = new CalculatorLoader();
    @Test
    public void parseBundle_whenFormatCorrect_thenReturnBundle(){
        String bundleString = "5 @ $130.00";
        Bundle result = loader.parseBundle(bundleString);
        assertEquals(5, result.getQuantity());
        assertEquals(130, result.getPrice());
    }

    @Test
    public void parseBundle_whenTooManyArguments_thenThrow(){
        String bundleString = "5 @ $130.00 23";
        assertThrows(IllegalArgumentException.class,
                () -> loader.parseBundle(bundleString));
    }
}

