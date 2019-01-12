package com.sb.shop.shop.shopping.infrastructure;

import com.sb.shop.shop.shopping.domain.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface SpDataShoppingCartsRepository extends CrudRepository<ShoppingCart, UUID>{
}
