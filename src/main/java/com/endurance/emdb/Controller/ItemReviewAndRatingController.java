package com.endurance.emdb.Controller;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemReviewAndRating;
import com.endurance.emdb.Service.ItemReviewAndRatingService;
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
        return new ResponseEntity<List<ItemReviewAndRating>>(itemReviewAndRatingsList, HttpStatus.OK);
    }

    @GetMapping(value = "top_rated_item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getTopRatedItemReviewAndRating(){
        Item topRatedItemReviewAndRating = itemReviewAndRatingService.getTopRatedItem();
        return new ResponseEntity<Item>(topRatedItemReviewAndRating, HttpStatus.OK);
    }

    @GetMapping(value = "top_rated_item/{x}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getTopXRatedItems(@PathVariable("x") int x){
        List<Item> topXRatedItems = itemReviewAndRatingService.getTopXItemsByRatings(x);
        return new ResponseEntity<List<Item>>(topXRatedItems, HttpStatus.OK);
    }

    @GetMapping(value = "average_rating/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getAverageRatingOnItem(@PathVariable("item_id") int item_id) {
        Double averageRating = itemReviewAndRatingService.getAverageRatingOfItem(item_id);
        return new ResponseEntity<Double>(averageRating, HttpStatus.OK);
    }

    @PostMapping(value = "{user_id}/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemReviewAndRating> createItem(@RequestBody ItemReviewAndRating itemReviewAndRating, @PathVariable("user_id") int user_id, @PathVariable("item_id") int item_id){
        ItemReviewAndRating createdItem = itemReviewAndRatingService.createReviewAndRating(itemReviewAndRating, user_id, item_id);
        return new ResponseEntity<ItemReviewAndRating>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping(value = "{user_id}/{item_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemReviewAndRating> updateItem(@RequestBody ItemReviewAndRating itemReviewAndRating, @PathVariable("user_id") int user_id, @PathVariable("item_id") int item_id){
        ItemReviewAndRating updatedItem = itemReviewAndRatingService.updateReviewAndRating(itemReviewAndRating, user_id, item_id);
        return new ResponseEntity<ItemReviewAndRating>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") int id){
        itemReviewAndRatingService.deleteItemReviewAndRating(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
