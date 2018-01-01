package com.endurance.emdb.controller;

import com.endurance.emdb.model.ItemCast;
import com.endurance.emdb.service.ItemCastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("item_cast")
public class ItemCastController {
    @Autowired
    private ItemCastService itemCastService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemCast>> getAllItems(){
        List<ItemCast> itemCasts = itemCastService.getAllItemCast();
        return new ResponseEntity<>(itemCasts, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCast> getItemCastByCastId(@PathVariable("id")int id){
        ItemCast itemCast = itemCastService.getItemCastById(id);
        return new ResponseEntity<>(itemCast, HttpStatus.OK);
    }

    @GetMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemCast>> getItemCastByItemId(@PathVariable("id") int id){
        List<ItemCast> itemCastList = itemCastService.getItemCastByItemId(id);
        return new ResponseEntity<>(itemCastList, HttpStatus.OK);
    }

    @PostMapping(value = "{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCast> createItemCast(@RequestBody ItemCast itemCast, @PathVariable("itemId") int itemId){
        ItemCast createdItemCast = itemCastService.createItemCast(itemCast, itemId);
        if (createdItemCast == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(createdItemCast, HttpStatus.OK);
    }

    @PutMapping(value = "{item_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCast> updateItemCast(@RequestBody ItemCast itemCast, @PathVariable("item_id") int itemId){
        ItemCast updatedItemCast = itemCastService.updateItemCast(itemCast, itemId);
        if (updatedItemCast == null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(updatedItemCast, HttpStatus.OK);
    }

    @DeleteMapping(value = "{itemCastId}")
    public ResponseEntity<Void> deleteItemCast(@PathVariable("id") int id){
        itemCastService.deleteItemCast(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
