package com.sb.shop.shop.shopping;

import com.sb.shop.shop.TestJsons.ItemTestJson;
import com.sb.shop.shop.TestJsons.NewItemTestJson;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("hb")
public class WithHbStorageShoppingControllerTest extends ShoppingControllerTest {


    @Test
    public void checkout_basket_removes_item_from_warehouse() {
        createNewItem(new NewItemTestJson("Item1", "2.4", 10));
        final String shoppingCardId = createShoppingCart();
        createShoppingItem(newShoppingItemJson("Item1", "2.4"), shoppingCardId);

        checkoutShoppingCart(shoppingCardId);

        assertThat(getWarehouseItems()).contains(new ItemTestJson("Item1", "2.4", 9));
    }

    @Test
    public void get_items_should_return_404_when_no_cart() {
        final String notExistingCartId = "4e393bb3-055f-4900-96cb-cbb344d21841";
        final ResponseEntity<String> response = restTemplate
                .exchange(format("/shopping-cart/%s/items/", notExistingCartId), HttpMethod.GET, null,
                        String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private List<ItemTestJson> getWarehouseItems() {
        final ResponseEntity<List<ItemTestJson>> response = restTemplate
                .exchange("/items/", HttpMethod.GET, null, new ParameterizedTypeReference<List<ItemTestJson>>() {
                });
        return response.getBody();
    }

    private void checkoutShoppingCart(final String shoppingCardId) {
        final ResponseEntity<Void> response = restTemplate
                .postForEntity(String.format("/shopping-cart/%s/checkout", shoppingCardId), null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    private ResponseEntity<Void> createNewItem(final NewItemTestJson newItem) {
        final ResponseEntity<Void> response = restTemplate.postForEntity("/items/", newItem, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        return response;
    }
}
