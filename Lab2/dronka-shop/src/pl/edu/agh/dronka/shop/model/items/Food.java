package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.enums.Category;

import java.util.Date;

public class Food extends Item {
    private static final Category CATEGORY = Category.FOOD;
    private final Date expiryDate;

    public Food(String name, int price, int quantity, Date expiryDate) {
        super(name, CATEGORY, price, quantity);
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}
