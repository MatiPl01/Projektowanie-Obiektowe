package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class Discount {
    private final BigDecimal discount;

    public Discount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage should be between 0 and 100, but got: " + discountPercentage);
        }
        this.discount = BigDecimal.valueOf(discountPercentage);
    }

    public BigDecimal getValue() {
        return discount;
    }

    public BigDecimal applyTo(BigDecimal price) {
        BigDecimal discountValue = discount
                .divide(BigDecimal.valueOf(100), Product.ROUND_STRATEGY)
                .setScale(Product.PRICE_PRECISION, Product.ROUND_STRATEGY);
        BigDecimal pricePercentage = BigDecimal.ONE.subtract(discountValue);

        return price.multiply(pricePercentage);
    }
}
