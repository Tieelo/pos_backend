package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {
	private Map<Item, Integer> items;
	private double totalCost;
	public Receipt(Cart cart) {
		this.items = cart.getItemsInCart();
		this.totalCost = cart.getTotalCost();
	}

	public Map<Item, Integer> getItems() {
		return items;
	}
	public List<String> getReceiptLines() {
		List<String> lines = new ArrayList<>();

		for (Map.Entry<Item, Integer> entry : items.entrySet()) {
			String line = String.format("%-20s %-30.2f€ %d",
					entry.getKey().getName(),
					entry.getKey().getPrice(),
					entry.getValue());

			lines.add(line);
		}

		return lines;
	}
	//private cart
	//cart to invoice
	//
}
