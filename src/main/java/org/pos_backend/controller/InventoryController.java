package org.pos_backend.controller;

import org.pos_backend.model.database.Inventory;
import org.pos_backend.model.objects.Groups;
import org.pos_backend.model.objects.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private final Inventory inventory;

	public InventoryController() {
		this.inventory = Inventory.getInstance();
	}

	@GetMapping("/groups")
	public ResponseEntity<List<Groups>> getGroups() {
		List<Groups> groups = inventory.getGroups();
		return ResponseEntity.ok(groups);
	}
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getItems(@RequestParam(required = false) Integer groupId) {
		List<Item> items = inventory.getItems(groupId);
		return ResponseEntity.ok(items);
	}
}
