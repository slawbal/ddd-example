package com.sb.shop.shop.shopping.infrastructure;

import com.sb.shop.shop.shopping.domain.ShoppingCarts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("hb")
public class WithHbStorageConfiguration {

	@Bean
	public ShoppingCarts shoppingCarts(SpDataShoppingCartsRepository repository) {
		return new HbShoppingCarts(repository);
	}

}
