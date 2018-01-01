package com.endurance.emdb.service;

import com.endurance.emdb.model.Item;
import com.endurance.emdb.model.ItemCast;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();

    List<Item> getItemsByType(String type);

    Item getItemById(int id);

    List<ItemCast> getItemCastByItemId(int id);

    Item createItem(Item item);

    Item updateItem(Item item);

    void deleteItem(int id);
}
