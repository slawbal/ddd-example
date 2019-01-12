package com.sb.shop.shop.shopping.domain;

import com.sb.shop.shop.shopping.application.EventPublisher;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ShoppingCart {
	@Id
	private UUID id;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<ShoppingItem> items = new ArrayList<>();

	public ShoppingCart() {
		id = UUID.randomUUID();
	}

	public String getId() {
		return this.id.toString();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final ShoppingCart that = (ShoppingCart) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

	public void add(final ShoppingItem shoppingItem) {
		items.add(shoppingItem);
	}

	public List<ShoppingItem> getItems() {
		return items;
	}

	public void checkout() {
		EventPublisher.publishEvent(new ShoppingCartCheckoutEvent(this));
	}
}
