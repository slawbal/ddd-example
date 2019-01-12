package com.sb.shop.shop.shopping.infrastructure;

import com.sb.shop.shop.shopping.domain.ShoppingCarts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("in-memory")
public class WithInMemoryStorageConfiguration {

	@Bean
	public ShoppingCarts shoppingCarts() {
		return new InMemoryShoppingCarts();
	}

}
