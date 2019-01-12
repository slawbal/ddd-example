package com.sb.shop.shop.warehouse.infrastructure;

import com.sb.shop.shop.warehouse.domain.Item;
import org.springframework.data.repository.CrudRepository;


public interface SpDataItemRepository extends CrudRepository<Item, Long> {
}
