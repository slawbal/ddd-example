package com.sb.shop.shop.shopping;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ShoppingControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void createNewShoppingCart() {
		final ResponseEntity<String> response = restTemplate
				.postForEntity("/shopping-cart/", "", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}

	@Test
	public void addItemToCart_shouldPersistThem() {
		final String firstShoppingCardId = createShoppingCart();
		final String secondShoppingCardId = createShoppingCart();
		createShoppingItem(newShoppingItemJson("item1", "2.40"), firstShoppingCardId);
		createShoppingItem(newShoppingItemJson("item2", "1.40"), firstShoppingCardId);
		createShoppingItem(newShoppingItemJson("item3", "3.40"), secondShoppingCardId);

		final List<ShoppingItemJson> firstShoppingItems = getItemsFor(firstShoppingCardId);
		final List<ShoppingItemJson> secondShoppingItems = getItemsFor(secondShoppingCardId);

		assertThat(firstShoppingItems).contains(
			new ShoppingItemJson("item1", "2.40"),
			new ShoppingItemJson("item2", "1.40")
		);
		assertThat(secondShoppingItems).contains(
				new ShoppingItemJson("item3", "3.40")
		);
	}

	protected List<ShoppingItemJson> getItemsFor(final String shoppingCardId) {
		final ResponseEntity<List<ShoppingItemJson>> response = restTemplate
				.exchange(format("/shopping-cart/%s/items/", shoppingCardId), HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ShoppingItemJson>>() {
						});
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		return response.getBody();
	}

	protected String createShoppingCart() {
		return restTemplate
				.postForEntity("/shopping-cart/", "", String.class).getBody();
	}

	protected ResponseEntity<Void> createShoppingItem(final NewShoppingItemJson shoppingItem,
			final String shoppingCartId) {
		final ResponseEntity<Void> response = restTemplate
				.postForEntity(format("/shopping-cart/%s/items/", shoppingCartId), shoppingItem, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		return response;
	}

	protected NewShoppingItemJson newShoppingItemJson(final String name, final String price) {
		return new NewShoppingItemJson(name, price);
	}

	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class NewShoppingItemJson {
		private final String name;
		private final String price;

		public NewShoppingItemJson(final String name, final String price) {
			this.name = name;
			this.price = price;
		}
	}

	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public static class ShoppingItemJson {
		private String name;
		private String price;

		public ShoppingItemJson() { }

		public ShoppingItemJson(final String name, final String price) {
			this.name = name;
			this.price = price;
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			final ShoppingItemJson that = (ShoppingItemJson) o;
			return Objects.equals(name, that.name) && Objects.equals(price, that.price);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, price);
		}

		@Override
		public String toString() {
			return "ShoppingItemJson{" + "name='" + name + '\'' + ", price='" + price + '\'' + '}';
		}
	}
}
