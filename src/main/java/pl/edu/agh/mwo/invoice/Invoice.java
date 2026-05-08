package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();


    private final String number = generateFvNumber();

    public String getNumber() {
        return number;
    }

    public String getPrint() {
        String print = this.getNumber() + "\n";
        for (Product product: products.keySet()) {
            print = print
                    + product.getName()
                    + ";"
                    + products.get(product)
                    + ";"
                    + product.getPrice()
                    + "\n";
        }
        print = print + "Liczba pozycji: " + products.size();

        return print;

    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }

        products.merge(product, quantity, Integer::sum);

    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    private String generateFvNumber() {
        //tymczasowy placeholder dla generowania nr faktury w danym formacie;
        LocalDate today = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        String datastr = today.format(format);
        Random rand = new Random();
        final int fvEndNumberScopeForRand = 1_000_000;
        String sixDigits = String.format("%06d", rand.nextInt(fvEndNumberScopeForRand));

        return "FV/" + datastr + "/" + sixDigits;
    }
}
