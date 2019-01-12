package com.sb.shop.shop;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;


public class TestJsons {
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class NewItemTestJson {
		private final String name;
		private final String price;
		private int quantity;

		public NewItemTestJson(final String name, final String price, final int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}
	}

	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class ItemTestJson {
		private String name;
		private String price;
		private int quantity;

		public ItemTestJson() { }

		public ItemTestJson(final String name, final String price, final int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;

		}

		@Override
		public boolean equals(final Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			final ItemTestJson that = (ItemTestJson) o;
			return quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(price, that.price);
		}

		@Override
		public int hashCode() {

			return Objects.hash(name, price, quantity);
		}

		@Override
		public String toString() {
			return "ItemTestJson{" + "name='" + name + '\'' + ", price='" + price + '\'' + ", quantity=" + quantity
					+ '}';
		}
	}
}
