package com.sb.shop.shop.warehouse.domain;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Item {
	@Id
	private String id;
	private String name;
	private String price;
	private int quantity;

	private Item() {
	}

	public Item(final String id, final String name, final String price, final int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void decreaseQuantity() {
		quantity = quantity - 1;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", price='" + price + '\'' +
				", quantity=" + quantity +
				'}';
	}
}
