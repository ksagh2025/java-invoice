package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleofWine extends Product {
    private BigDecimal akcyza = new BigDecimal("5.56");

    public BottleofWine(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.23"));
    }

    public BigDecimal getPriceWithTax() {
        return super.getPrice()
                .add(akcyza)
                .multiply(super.getTaxPercent())
                .add(super.getPrice())
                .add(akcyza);
    }

}
