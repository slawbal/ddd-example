package com.sb.shop.shop.shopping.infrastructure;

import com.sb.shop.shop.shopping.domain.ShoppingCart;
import com.sb.shop.shop.shopping.domain.ShoppingCarts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryShoppingCarts implements ShoppingCarts {

	private List<ShoppingCart> shoppingCarts = new ArrayList<>();

	@Override
	public void save(final ShoppingCart shoppingCart) {
		shoppingCarts.add(shoppingCart);
	}

	@Override
	public Optional<ShoppingCart> get(final String id) {
		return shoppingCarts.stream().filter(c -> c.getId().equals(id)).findFirst();
	}
}
