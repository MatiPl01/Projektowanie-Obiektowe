package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

import java.math.BigDecimal;


public class ProductTest {
    private static final String NAME = "Mr. Sparkle";
    private static final BigDecimal PRICE = BigDecimal.valueOf(1);
    private static final Discount DISCOUNT = new Discount(10);
    private static final BigDecimal PRICE_WITH_DISCOUNT = BigDecimal.valueOf(.9);

    @Test
    public void getProductName() {
        //given
    	
        // when
        Product product = new Product(NAME, PRICE);
        
        // then
        assertEquals(NAME, product.getName());
    }

    @Test
    public void getProductDiscount() {
        // given

        // when
        Product product = new Product(NAME, PRICE, DISCOUNT);

        // then
        assertBigDecimalCompareValue(product.getDiscount().getValue(), DISCOUNT.getValue());
    }

    @Test
    public void getProductPrice() throws Exception {
        // given

        // when
        Product product = new Product(NAME, PRICE, DISCOUNT);

        // then
        assertBigDecimalCompareValue(product.getPrice(), PRICE_WITH_DISCOUNT);
    }

    @Test
    public void getPriceWithoutDiscount() {
        // given

        // when
        Product product = new Product(NAME, PRICE);

        // then
        assertBigDecimalCompareValue(product.getPriceWithoutDiscount(), PRICE);
    }
}