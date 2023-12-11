package org.pos_backend.model.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Receipt {
	private Cart cart = Cart.getInstance();

	public double getTotalCost() {
		return cart.getTotalCost();
	}

	public int getTotalAmount() {
		return cart.getItemCount();
	}

	public Map<Item, Integer> getItems() {
		return cart.getItemsInCart();
	}

	public List<String> getReceiptLines() {
		List<String> lines = new ArrayList<>();
		Map<Item, Integer> items = getItems();

		for (Map.Entry<Item, Integer> entry : items.entrySet()) {
			String line = String.format("%-19s %.2f€ %-12s %2d",
					entry.getKey().getName(),
					entry.getKey().getPrice(),
					"",
					entry.getValue());

			lines.add(line);
		}

		return lines;
	}
}