package com.sb.shop.shop.warehouse.application;

import com.sb.shop.shop.shopping.domain.ShoppingCartCheckoutEvent;
import com.sb.shop.shop.warehouse.domain.IdGenerator;
import com.sb.shop.shop.warehouse.domain.Item;
import com.sb.shop.shop.warehouse.domain.ItemRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class WarehouseController {

	private List<ItemJson> items = new ArrayList<>();
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	IdGenerator idGenerator;

	@PostMapping("/items/")
	public void addItem(@RequestBody final ItemJson itemJson) {
		itemRepository.save(new Item(idGenerator.nextId(), itemJson.name, itemJson.price, itemJson.quantity));
	}

	@GetMapping("/items/")
	public List<ItemJson> getItems() {
		return itemRepository.all().stream().map(e -> map(e)).collect(toList());
	}

	private ItemJson map(final Item item) {
		return new ItemJson(item.getName(), item.getPrice(), item.getQuantity());
	}

	@EventListener(ShoppingCartCheckoutEvent.class)
	@Transactional
	public void updateQuantity(final ShoppingCartCheckoutEvent event){
		final List<String> itemNames = event.getItems().stream().map(shoppingItem -> shoppingItem.getName()).collect(toList());
		itemRepository.all().stream().filter(i -> itemNames.contains(i.getName())).forEach(item -> item.decreaseQuantity());

	}
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class ItemJson {
		private String name;
		private String price;
		private int quantity;

		public ItemJson() {
		}

		public ItemJson(final String name, final String price, final int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}


	}
}
