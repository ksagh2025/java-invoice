package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("product cannot be null");
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null) throw new IllegalArgumentException("product cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity cannot be zero");
        for (int i = 0; i < quantity; i++) {
        products.add(product);}
    }

    public BigDecimal getSubtotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getPrice());
        }
        return total;
    }

    public BigDecimal getTax() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getPriceWithTax().subtract(product.getPrice()));
        }
        return total;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getPriceWithTax());
        }
        return total;
    }
}
