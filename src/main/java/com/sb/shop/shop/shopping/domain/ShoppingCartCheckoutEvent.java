package com.sb.shop.shop.shopping.domain;

import org.springframework.context.ApplicationEvent;

import java.util.List;


public class ShoppingCartCheckoutEvent extends ApplicationEvent {
	private ShoppingCart shoppingCart;

	public ShoppingCartCheckoutEvent(final ShoppingCart shoppingCart) {
		super(ShoppingCartCheckoutEvent.class);
		this.shoppingCart = shoppingCart;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public List<ShoppingItem> getItems() {
		return shoppingCart.getItems();
	}
}
