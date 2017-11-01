package com.endurance.emdb.Service;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;
import com.endurance.emdb.Repository.ItemCastRepository;
import com.endurance.emdb.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceBean implements ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemCastRepository itemCastRepository;

    @Override
    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByType(String type) {
        return itemRepository.findItemsByType(type);
    }

    @Override
    public Item getItemById(int id) {
        return itemRepository.findOne(id);
    }

    @Override
    public List<ItemCast> getItemCastByItemId(int id) {
        return itemCastRepository.findItemCastByItemId(id);
    }

    @Override
    public Item createItem(Item item) {
        if (item.getId() != 0) {
            return null;
        }
        Item createdItem = itemRepository.save(item);
        return createdItem;
    }

    @Override
    public Item updateItem(Item item) {
        if (item.getId() == 0) {
            return null;
        }
        Item itemToUpdate = itemRepository.findOne(item.getId());
        itemToUpdate.setName(item.getName());
        itemToUpdate.setType(item.getType());
        itemRepository.save(itemToUpdate);
        return itemToUpdate;
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.delete(id);
    }
}
