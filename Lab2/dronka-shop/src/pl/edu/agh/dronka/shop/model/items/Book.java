package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.enums.Category;

public class Book extends Item {
    private static final Category CATEGORY = Category.BOOKS;
    private final int noPages;
    private final boolean hardCover;

    public Book(String name, int price, int quantity, int noPages, boolean hardCover) {
        super(name, CATEGORY, price, quantity);
        this.noPages = noPages;
        this.hardCover = hardCover;
    }

    public int getNoPages() {
        return noPages;
    }

    public boolean isHardCover() {
        return hardCover;
    }
}
