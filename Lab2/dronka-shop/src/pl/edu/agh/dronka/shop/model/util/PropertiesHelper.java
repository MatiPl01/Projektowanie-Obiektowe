package pl.edu.agh.dronka.shop.model.util;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.dronka.shop.model.items.*;

public class PropertiesHelper {
	private	static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

	public static Map<String, Object> getPropertiesMap(Item item) {
		Map<String, Object> propertiesMap = new LinkedHashMap<>();
		
		propertiesMap.put("Nazwa", item.getName());
		propertiesMap.put("Cena", item.getPrice());
		propertiesMap.put("Kategoria", item.getCategory().getDisplayName()); 
		propertiesMap.put("Ilość", Integer.toString(item.getQuantity()));
		propertiesMap.put("Tanie bo polskie", item.isPolish());
		propertiesMap.put("Używany", item.isSecondhand());

		// Add category-specific properties
		switch (item.getCategory()) {
			case BOOKS -> {
				Book book = (Book) item;
				propertiesMap.put("Liczba stron", book.getNoPages());
				propertiesMap.put("Twarda oprawa", book.isHardCover());
			}
			case ELECTRONICS -> {
				Electronic electronic = (Electronic) item;
				propertiesMap.put("Mobilny", electronic.isMobile());
				propertiesMap.put("Gwarancja", electronic.hasWarranty());
			}
			case FOOD -> {
				Food food = (Food) item;
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				String dateStr = sdf.format(food.getExpiryDate());
				propertiesMap.put("Data przydatności", dateStr);
			}
			case MUSIC -> {
				Music music = (Music) item;
				propertiesMap.put("Gatunek muzyczny", music.getGenre().getDisplayName());
				propertiesMap.put("Ma teledysk", music.hasVideo());
			}
		}
		
		return propertiesMap;
	}
}
