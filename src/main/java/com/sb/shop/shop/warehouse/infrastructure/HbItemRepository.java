package com.sb.shop.shop.warehouse.infrastructure;

import com.sb.shop.shop.warehouse.domain.Item;
import com.sb.shop.shop.warehouse.domain.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Repository
public class HbItemRepository implements ItemRepository {

	@Autowired
	SpDataItemRepository repository;

	@Override
	public void save(final Item item) {
		repository.save(item);
	}

	@Override
	public List<Item> all() {
		return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
	}
}
