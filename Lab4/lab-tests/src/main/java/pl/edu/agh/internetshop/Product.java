package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
	public static final int PRICE_PRECISION = 2;
	public static final RoundingMode ROUND_STRATEGY = RoundingMode.HALF_UP;
	
    private final String name;
    private final BigDecimal priceWithoutDiscount;
    private BigDecimal discountedPrice;
    private Discount discount = new Discount(0);

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.priceWithoutDiscount = price.setScale(PRICE_PRECISION, ROUND_STRATEGY);
        this.discountedPrice = price;
    }

    public Product(String name, BigDecimal price, Discount discount) {
        this(name, price);
        this.discount = discount;
        this.discountedPrice = discount.applyTo(price);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return discountedPrice;
    }

    public BigDecimal getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public Discount getDiscount() {
        return discount;
    }
}
