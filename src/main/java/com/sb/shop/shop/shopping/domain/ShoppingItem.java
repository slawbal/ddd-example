package com.sb.shop.shop.shopping.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;


@Entity
public class ShoppingItem {
	@Id
	private UUID id;
	private String name;
	private String price;

	private ShoppingItem() { }

	public ShoppingItem(final String name, final String price) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.price = price;
	}

	public String getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
}
