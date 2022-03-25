package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.enums.Category;

public class Sport extends Item {
    private static final Category CATEGORY = Category.SPORT;

    public Sport(String name, int price, int quantity) {
        super(name, CATEGORY, price, quantity);
    }
}
