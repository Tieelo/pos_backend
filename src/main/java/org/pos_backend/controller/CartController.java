package org.pos_backend.controller;

import org.pos_backend.model.objects.Cart;
import org.pos_backend.model.objects.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private final Cart cart;

	public CartController() {
		this.cart = Cart.getInstance();
	}

	@PostMapping("/fill")
	public ResponseEntity<Void> fillCart(@RequestBody int[] idAndAmount) {
		cart.fillCart(idAndAmount);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/sell")
	public ResponseEntity<Void> sellCart() {
		cart.sellCart();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/total")
	public ResponseEntity<Double> getTotalCost() {
		double total = cart.getTotalCost();
		return ResponseEntity.ok(total);
	}

	@PostMapping("/items/remove/{id}/{amount}")
	public ResponseEntity<Void> removeItemById(@RequestBody int [] idAndAmount){
		cart.removeItemById(idAndAmount);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/return")
	public ResponseEntity<Void> putCartBackToInventory() {
		cart.putCartBackToInventory();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/count")
	public ResponseEntity<Integer> getItemCount() {
		int count = cart.getItemCount();
		return ResponseEntity.ok(count);
	}

	@GetMapping("/items")
	public ResponseEntity<List<CartItem>> getItemsInCart() {
		List<CartItem> items = cart.getItemsFromCart();
		return ResponseEntity.ok(items);
	}
}