package pl.edu.agh.dronka.shop.model.provider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.edu.agh.dronka.shop.model.enums.Category;
import pl.edu.agh.dronka.shop.model.Index;
import pl.edu.agh.dronka.shop.model.Shop;
import pl.edu.agh.dronka.shop.model.User;
import pl.edu.agh.dronka.shop.model.enums.MusicGenre;
import pl.edu.agh.dronka.shop.model.items.*;

public class ShopProvider {
	private	static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

	public static Shop getExampleShop() {
		Shop shop = new Shop();

		shop.addUser(getExampleUser());

		Index itemsIndex = new Index();

		for (Item item : getExampleItems()) {
			itemsIndex.addItem(item);
		}

		registerExampleCategories(itemsIndex);
		shop.setItemsIndex(itemsIndex);

		return shop;
	}

	public static User getExampleUser() {
		return new User("Jan", "Rejnor");
	}

	public static List<Item> getExampleItems() {
		List<Item> items = new ArrayList<>();

		CSVReader booksReader = new CSVReader("resources/books.csv");
		items.addAll(readItems(booksReader, Category.BOOKS));
		
		CSVReader electronicsReader = new CSVReader("resources/electronics.csv");
		items.addAll(readItems(electronicsReader, Category.ELECTRONICS));
		
		CSVReader foodReader = new CSVReader("resources/food.csv");
		items.addAll(readItems(foodReader, Category.FOOD));
		
		CSVReader musicReader = new CSVReader("resources/music.csv");
		items.addAll(readItems(musicReader, Category.MUSIC));
		
		CSVReader sportReader = new CSVReader("resources/sport.csv");
		items.addAll(readItems(sportReader, Category.SPORT));

		return items;
	}

	public static void registerExampleCategories(Index index) {
		for (Category category : Category.values()) {
			index.registerCategory(category);
		}
	}

	private static List<Item> readItems(CSVReader reader, Category category) {
		List<Item> items = new ArrayList<>();

		try {
			reader.parse();
			List<String[]> data = reader.getData();

			for (String[] dataLine: data) {
				Item item = createItem(reader, dataLine, category);

				boolean isPolish = Boolean.parseBoolean(reader.getValue(dataLine, "Tanie bo polskie"));
				boolean isSecondhand = Boolean.parseBoolean(reader.getValue(dataLine, "Używany"));

				item.setPolish(isPolish);
				item.setSecondhand(isSecondhand);

				items.add(item);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return items;
	}

	private static Item createItem(CSVReader reader, String[] dataLine, Category category) {
		String name = reader.getValue(dataLine,"Nazwa");
		int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
		int quantity = Integer.parseInt(reader.getValue(dataLine, "Ilość"));

		switch (category) {
			case BOOKS -> {
				int noPages = Integer.parseInt(reader.getValue(dataLine, "Liczba stron"));
				boolean hardCover = Boolean.parseBoolean(reader.getValue(dataLine, "Twarda oprawa"));
				return new Book(name, price, quantity, noPages, hardCover);
			}
			case ELECTRONICS -> {
				boolean isMobile = Boolean.parseBoolean(reader.getValue(dataLine, "Mobilny"));
				boolean hasWarranty = Boolean.parseBoolean(reader.getValue(dataLine, "Gwarancja"));
				return new Electronic(name, price, quantity, isMobile, hasWarranty);
			}
			case FOOD -> {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				String dateStr = reader.getValue(dataLine, "Data przydatności");
				Date expiryDate;
				try {
					expiryDate = sdf.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
					expiryDate = new Date(0);
				}
				return new Food(name, price, quantity, expiryDate);
			}
			case MUSIC -> {
				String genreStr = reader.getValue(dataLine, "Gatunek muzyczny");
				MusicGenre genre = MusicGenre.valueOf(genreStr.toUpperCase());
				boolean hasVideo = Boolean.parseBoolean(reader.getValue(dataLine, "Ma teledysk"));
				return new Music(name, price, quantity, genre, hasVideo);
			}
			case SPORT -> {
				return new Sport(name, price, quantity);
			}
			default -> throw new IllegalArgumentException(category + " is not a valid category");
		}
	}
}
