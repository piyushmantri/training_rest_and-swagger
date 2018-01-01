package com.endurance.emdb.controller;

import com.endurance.emdb.model.Item;
import com.endurance.emdb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getItemById(@PathVariable("id") int id){
        Item item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item createdItem =  itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> updateItem(@RequestBody Item item){
        Item updatedItem = itemService.updateItem(item);
        if (updatedItem == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") int id){
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
