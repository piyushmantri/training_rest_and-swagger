package com.endurance.emdb.Service;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;
import com.endurance.emdb.Repository.ItemCastRepository;
import com.endurance.emdb.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCastServiceBean implements ItemCastService{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCastRepository itemCastRepository;

    @Override
    public List<ItemCast> getAllItemCast() {
        return itemCastRepository.findAllItemCasts();
    }

    @Override
    public ItemCast getItemCastById(int id) {
        return itemCastRepository.findById(id);
    }

    @Override
    public List<ItemCast> getItemCastByItemId(int id) {
        return itemCastRepository.findItemCastByItemId(id);
    }

    @Override
    public List<Item> getItemsByItemCast(String cast) {
        return itemCastRepository.findItemsByCastName(cast);
    }

    @Override
    public Item getItemByCastId(int id) {
        return itemCastRepository.findOne(id);
    }

    @Override
    public ItemCast createItemCast(ItemCast itemCast, int item_id) {
        if (itemCast.getId() != 0){
            return null;
        }
        ItemCast createdItemCast = create(itemCast, item_id);
        return createdItemCast;
    }

    private ItemCast create(ItemCast itemCast, int id){
        Item item = itemRepository.findOne(id);
        if (item == null){
            return null;
        }
        ItemCast createdItemCast = new ItemCast();
        createdItemCast.setItem(item);
        createdItemCast.setCastName(itemCast.getCastName());
        createdItemCast.setRole(itemCast.getRole());
        itemCastRepository.save(createdItemCast);
        return createdItemCast;
    }

    @Override
    public ItemCast updateItemCast(ItemCast itemCast, int item_id) {
        if (itemCast.getId() == 0){
            return null;
        }
        ItemCast updatedItemCast = update(itemCast, item_id);
        return updatedItemCast;
    }

    private ItemCast update(ItemCast itemCast, int item_id){
        Item item = itemRepository.findOne(item_id);
        ItemCast itemToUpdate = itemCastRepository.findById(itemCast.getId());
        if (item == null || itemToUpdate == null){
            return null;
        }
        itemToUpdate.setItem(item);
        itemToUpdate.setCastName(itemCast.getCastName());
        itemToUpdate.setRole(itemCast.getRole());
        itemCastRepository.save(itemToUpdate);
        return itemToUpdate;
    }

    @Override
    public ItemCast deleteItemCast(int id) {
        return null;
    }
}
