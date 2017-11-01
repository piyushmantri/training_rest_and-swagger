package com.endurance.emdb.Service;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;

import java.util.List;

public interface ItemCastService {
    List<ItemCast> getAllItemCast();

    ItemCast getItemCastById(int id);

    List<ItemCast> getItemCastByItemId(int id);

    List<Item> getItemsByItemCast(String cast);

    Item getItemByCastId(int id);

    ItemCast createItemCast(ItemCast itemCast, int item_id);

    ItemCast updateItemCast(ItemCast itemCast, int item_id);

    ItemCast deleteItemCast(int id);

}
