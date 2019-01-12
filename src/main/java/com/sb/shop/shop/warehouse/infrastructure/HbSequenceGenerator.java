package com.sb.shop.shop.warehouse.infrastructure;

import com.sb.shop.shop.warehouse.domain.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class HbSequenceGenerator implements IdGenerator {

	@Override
	public String nextId() {
		return UUID.randomUUID().toString();
	}
}
