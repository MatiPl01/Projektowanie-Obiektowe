package pl.edu.agh.dronka.shop.view;

import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.enums.Category;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;
import pl.edu.agh.dronka.shop.model.util.PropertiesHelper;

public class PropertiesPanel extends JPanel {

	@Serial
	private static final long serialVersionUID = -2804446079853846996L;
	private final ShopController shopController;

	private final ItemFilter filter = new ItemFilter();

	public PropertiesPanel(ShopController shopController) {
		this.shopController = shopController;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void fillProperties() {
		removeAll();

		Category category = shopController.getCurrentCategory();
		filter.setCategory(category);

		// Add all category specific properties
		for (String propertyName: PropertiesHelper.getBooleanCategoryProperties(category)) {
			add(createPropertyCheckbox(propertyName, event -> {
				filter.setFilter(propertyName, ((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}));
		}
	}

	private JCheckBox createPropertyCheckbox(String propertyName, ActionListener actionListener) {
		JCheckBox checkBox = new JCheckBox(propertyName);
		checkBox.setSelected(false);
		checkBox.addActionListener(actionListener);

		return checkBox;
	}
}
