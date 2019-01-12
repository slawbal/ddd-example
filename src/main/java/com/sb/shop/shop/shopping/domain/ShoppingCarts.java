package com.sb.shop.shop.shopping.domain;

import java.util.Optional;

public interface ShoppingCarts {
	void save(ShoppingCart shoppingCart);
	Optional<ShoppingCart> get(String id);
}
