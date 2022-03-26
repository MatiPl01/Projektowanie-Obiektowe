package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.enums.Category;
import pl.edu.agh.dronka.shop.model.items.Item;
import pl.edu.agh.dronka.shop.model.util.PropertiesHelper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemFilter {
	private Category category;
	private Set<String> appliedFilters;

	public void setCategory(Category category) {
		this.category = category;
		appliedFilters = new HashSet<>();
	}

	public void setFilter(String propertyName, boolean value) {
		if (value) appliedFilters.add(propertyName);
		else appliedFilters.remove(propertyName);
	}

	public boolean appliesTo(Item item) {
		if (item.getCategory() != category) return false;

		Map<String, Object> itemPropertiesMap = PropertiesHelper.getItemPropertiesMap(item);

		for (String propertyName: appliedFilters) {
			if (!(boolean) itemPropertiesMap.get(propertyName)) return false;
		}
		return true;
	}
}
