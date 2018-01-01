package com.endurance.emdb.service;

import com.endurance.emdb.model.Item;
import com.endurance.emdb.model.ItemReviewAndRating;

import java.util.List;

public interface ItemReviewAndRatingService {
    List<ItemReviewAndRating> getAllReviewsAndRatings();

    List<Item> getTopXItemsByRatings(int x);

    Item getTopRatedItem();

    Double getAverageRatingOfItem(int itemId);

    ItemReviewAndRating createReviewAndRating(ItemReviewAndRating itemReviewAndRating, int userId, int itemId);

    ItemReviewAndRating updateReviewAndRating(ItemReviewAndRating itemReviewAndRating, int userId, int itemId);

    void deleteItemReviewAndRating(int id);
}
