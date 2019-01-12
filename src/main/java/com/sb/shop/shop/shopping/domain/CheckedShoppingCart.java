package com.sb.shop.shop.shopping.domain;

import java.util.List;

public class CheckedShoppingCart {
    private final List<ShoppingItem> items;

    public CheckedShoppingCart(List<ShoppingItem> items) {
        this.items = items;
    }

    public List<ShoppingItem> getItems() {
        return this.items;
    }
}
