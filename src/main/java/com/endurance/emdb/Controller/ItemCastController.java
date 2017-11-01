package com.endurance.emdb.Controller;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;
import com.endurance.emdb.Service.ItemCastService;
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
        return new ResponseEntity<List<ItemCast>>(itemCasts, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCast> getItemCastByCastId(@PathVariable("id")int id){
        ItemCast itemCast = itemCastService.getItemCastById(id);
        return new ResponseEntity<ItemCast>(itemCast, HttpStatus.OK);
    }

    @GetMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemCast>> getItemCastByItemId(@PathVariable("id") int id){
        List<ItemCast> itemCastList = itemCastService.getItemCastByItemId(id);
        return new ResponseEntity<List<ItemCast>>(itemCastList, HttpStatus.OK);
    }

    @PostMapping(value = "{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCast> createItemCast(@RequestBody ItemCast itemCast, @PathVariable("itemId") int itemId){
        ItemCast createdItemCast = itemCastService.createItemCast(itemCast, itemId);
        if (createdItemCast == null){
            return new ResponseEntity<ItemCast>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<ItemCast>(createdItemCast, HttpStatus.OK);
    }

    @PutMapping(value = "{item_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemCast> updateItemCast(@RequestBody ItemCast itemCast, @PathVariable("item_id") int itemId){
        ItemCast updatedItemCast = itemCastService.updateItemCast(itemCast, itemId);
        if (updatedItemCast == null){
            return new ResponseEntity<ItemCast>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<ItemCast>(updatedItemCast, HttpStatus.OK);
    }

    @DeleteMapping(value = "{itemCastId}")
    public ResponseEntity<Void> deleteItemCast(@PathVariable("id") int id){
        itemCastService.deleteItemCast(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
