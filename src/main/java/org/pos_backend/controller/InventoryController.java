package org.pos_backend.controller;

import org.pos_backend.model.database.Inventory;
import org.pos_backend.model.objects.Groups;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InventoryController {

	private final Inventory inventory;

	public InventoryController() {
		this.inventory = Inventory.getInstance();
	}

	@GetMapping("/groups")
	public List<Groups> getGroups() {
		return inventory.getGroups();
	}
}
