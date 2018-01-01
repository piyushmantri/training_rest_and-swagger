package com.endurance.emdb.service;

import com.endurance.emdb.model.Item;
import com.endurance.emdb.model.ItemCast;
import com.endurance.emdb.repository.ItemCastRepository;
import com.endurance.emdb.repository.ItemRepository;
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
    public ItemCast createItemCast(ItemCast itemCast, int itemId) {
        if (itemCast.getId() != 0){
            return null;
        }
        ItemCast createdItemCast = create(itemCast, itemId);
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
    public ItemCast updateItemCast(ItemCast itemCast, int itemId) {
        if (itemCast.getId() == 0){
            return null;
        }
        ItemCast updatedItemCast = update(itemCast, itemId);
        return updatedItemCast;
    }

    private ItemCast update(ItemCast itemCast, int itemId){
        Item item = itemRepository.findOne(itemId);
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
