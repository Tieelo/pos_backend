package model;

import java.util.HashMap;
import java.util.Map;

public class Receipt {
	private Map<Item, Integer> items;
	public Receipt(Cart cart) {
		this.items = new HashMap<>(cart.getItemsInCart());
	}

	//private cart
	//cart to invoice
	//
}
