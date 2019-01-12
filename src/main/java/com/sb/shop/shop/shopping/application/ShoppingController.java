package com.sb.shop.shop.shopping.application;

import com.sb.shop.shop.shopping.domain.ShoppingCart;
import com.sb.shop.shop.shopping.domain.ShoppingCarts;
import com.sb.shop.shop.shopping.domain.ShoppingItem;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ShoppingController {

	@Autowired
	private ShoppingCarts shoppingCarts;

	@PostMapping("/shopping-cart/")
	public String createShoppingCart() {
		final ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCarts.save(shoppingCart);
		return shoppingCart.getId();
	}

	@PostMapping("/shopping-cart/{id}/items")
	@Transactional
	public void addItem(@PathVariable final String id, @RequestBody final ShoppingItemJson shoppingItemJson) {
		shoppingCarts.get(id).orElseThrow(() ->  new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Cart Not Found")).add(new ShoppingItem(shoppingItemJson.name, shoppingItemJson.price));
	}

	@GetMapping("/shopping-cart/{id}/items")
	public List<ShoppingItemJson> getItems(@PathVariable final String id) {
		return shoppingCarts.get(id).orElseThrow(() ->  new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Cart Not Found")).getItems().stream()
				.map(c -> new ShoppingItemJson(c.getName(), c.getPrice())).collect(Collectors.toList());
	}

	@PostMapping("/shopping-cart/{id}/checkout")
	public void checkout(@PathVariable final String id){
		shoppingCarts.get(id).orElseThrow(() ->  new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Cart Not Found")).checkout();
	}

	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class ShoppingItemJson {
		private String name;
		private String price;

		public ShoppingItemJson() {
		}

		public ShoppingItemJson(final String name, final String price) {
			this.name = name;
			this.price = price;
		}
	}

}
