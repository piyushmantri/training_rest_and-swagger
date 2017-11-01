package com.endurance.emdb.Service;


import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;

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
