package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {
    private BigDecimal akcyza = new BigDecimal("5.56");

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
    }

    public BigDecimal getPriceWithTax() {
        return super.getPrice()
                .add(akcyza)
                .multiply(super.getTaxPercent())
                .add(super.getPrice())
                .add(akcyza);
    }

}
