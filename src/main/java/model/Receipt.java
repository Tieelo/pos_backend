package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {
	private Map<Item, Integer> items;
	private double totalCost;
	private int totalAmount;
	Cart cart = Cart.getInstance();

	public double getTotalCost() {
		return totalCost;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public Receipt() {
		items = cart.getItemsInCart();
		totalCost = cart.getTotalCost();
		totalAmount = cart.getItemCount();
	}

	public Map<Item, Integer> getItems() {
		return items;
	}
	public List<String> getReceiptLines() {
		List<String> lines = new ArrayList<>();

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
	//private cart
	//cart to invoice
	//
}
