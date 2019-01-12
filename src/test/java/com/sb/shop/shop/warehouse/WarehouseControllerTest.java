package com.sb.shop.shop.warehouse;

import com.sb.shop.shop.TestJsons;
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

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WarehouseControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void addItemToWarehouse_shouldPersist() {
		createNewItem(new TestJsons.NewItemTestJson("item1", "2,40", 5));
		createNewItem(new TestJsons.NewItemTestJson("item2", "3,40", 10));

		final ResponseEntity<List<TestJsons.ItemTestJson>> response = restTemplate
				.exchange("/items/", HttpMethod.GET, null, new ParameterizedTypeReference<List<TestJsons.ItemTestJson>>() {});

		assertThat(response.getBody()).contains(
				new TestJsons.ItemTestJson("item1", "2,40", 5),
				new TestJsons.ItemTestJson("item2", "3,40", 10)
		);
	}

	private ResponseEntity<Void> createNewItem(final TestJsons.NewItemTestJson newItem) {
		final ResponseEntity<Void> response = restTemplate.postForEntity("/items/", newItem, Void.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		return response;
	}

}
