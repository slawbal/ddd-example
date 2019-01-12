package com.sb.shop.shop.warehouse.domain;

import java.util.List;

public interface ItemRepository {

	void save(Item item);
	List<Item> all();
}
