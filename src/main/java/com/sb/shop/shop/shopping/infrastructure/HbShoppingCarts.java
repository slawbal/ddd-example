package com.sb.shop.shop.shopping.infrastructure;

import com.sb.shop.shop.shopping.domain.ShoppingCart;
import com.sb.shop.shop.shopping.domain.ShoppingCarts;

import java.util.Optional;
import java.util.UUID;


public class HbShoppingCarts implements ShoppingCarts {

	final SpDataShoppingCartsRepository repository;

	public HbShoppingCarts(final SpDataShoppingCartsRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(final ShoppingCart shoppingCart) {
		repository.save(shoppingCart);
	}

	@Override
	public Optional<ShoppingCart> get(final String id) {
		return repository.findById(UUID.fromString(id));
	}
}
