package org.pos_backend.model.objects;

public class CartItem {
	private final Item item;
	private final int amountInCart;

	// Konstruktor
	public CartItem(Item item, int amountInCart) {
		this.item = item;
		this.amountInCart = amountInCart;
	}

	// Getter
	public Item getItem() {
		return this.item;
	}

	public int getAmountInCart() {
		return this.amountInCart;
	}
}
