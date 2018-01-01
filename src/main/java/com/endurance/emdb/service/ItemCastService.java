package com.endurance.emdb.service;

import com.endurance.emdb.model.Item;
import com.endurance.emdb.model.ItemCast;

import java.util.List;

public interface ItemCastService {
    List<ItemCast> getAllItemCast();

    ItemCast getItemCastById(int id);

    List<ItemCast> getItemCastByItemId(int id);

    List<Item> getItemsByItemCast(String cast);

    Item getItemByCastId(int id);

    ItemCast createItemCast(ItemCast itemCast, int itemId);

    ItemCast updateItemCast(ItemCast itemCast, int itemId);

    ItemCast deleteItemCast(int id);

}
