package com.sb.shop.shop.setup;

import com.sb.shop.shop.warehouse.domain.Item;
import com.sb.shop.shop.warehouse.domain.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        itemRepository.save(new Item("1","Rubber duck", "20",50));
        itemRepository.save(new Item("2","Beer", "10",50));
    }
}
