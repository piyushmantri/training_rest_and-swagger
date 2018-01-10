package com.endurance.emdb.controller;

import com.endurance.emdb.model.Item;
import com.endurance.emdb.model.ItemReviewAndRating;
import com.endurance.emdb.service.ItemReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("review_and_rating")
public class ItemReviewAndRatingController {
    @Autowired
    private ItemReviewAndRatingService itemReviewAndRatingService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemReviewAndRating>> getAllReviewsAndRatings(){
        List<ItemReviewAndRating> itemReviewAndRatingsList = itemReviewAndRatingService.getAllReviewsAndRatings();
        return new ResponseEntity<>(itemReviewAndRatingsList, HttpStatus.OK);
    }

    @GetMapping(value = "top_rated_item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getTopRatedItemReviewAndRating(){
        Item topRatedItemReviewAndRating = itemReviewAndRatingService.getTopRatedItem();
        return new ResponseEntity<>(topRatedItemReviewAndRating, HttpStatus.OK);
    }

    @GetMapping(value = "top_rated_item/{x}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getTopXRatedItems(@PathVariable("x") int x){
        List<Item> topXRatedItems = itemReviewAndRatingService.getTopXItemsByRatings(x);
        return new ResponseEntity<>(topXRatedItems, HttpStatus.OK);
    }

    @GetMapping(value = "average_rating/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getAverageRatingOnItem(@PathVariable("itemId") int itemId) {
        Double averageRating = itemReviewAndRatingService.getAverageRatingOfItem(itemId);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @PostMapping(value = "{user_id}/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemReviewAndRating> createItem(@RequestBody ItemReviewAndRating itemReviewAndRating, @PathVariable("user_id") int userId, @PathVariable("item_id") int itemId){
        ItemReviewAndRating createdItem = itemReviewAndRatingService.createReviewAndRating(itemReviewAndRating, userId, itemId);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping(value = "{user_id}/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemReviewAndRating> updateItem(@RequestBody ItemReviewAndRating itemReviewAndRating, @PathVariable("user_id") int userId, @PathVariable("item_id") int itemId){
        ItemReviewAndRating updatedItem = itemReviewAndRatingService.updateReviewAndRating(itemReviewAndRating, userId, itemId);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") int id){
        itemReviewAndRatingService.deleteItemReviewAndRating(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
