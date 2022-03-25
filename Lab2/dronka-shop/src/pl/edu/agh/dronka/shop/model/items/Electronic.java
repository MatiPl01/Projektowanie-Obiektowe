package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.enums.Category;

public class Electronic extends Item {
    private static final Category CATEGORY = Category.ELECTRONICS;
    private final boolean mobile;
    private final boolean warranty;

    public Electronic(String name, int price, int quantity, boolean mobile, boolean warranty) {
        super(name, CATEGORY, price, quantity);
        this.mobile = mobile;
        this.warranty = warranty;
    }

    public boolean isMobile() {
        return mobile;
    }

    public boolean hasWarranty() {
        return warranty;
    }
}
