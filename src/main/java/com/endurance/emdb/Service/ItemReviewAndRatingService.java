package com.endurance.emdb.Service;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemReviewAndRating;

import java.util.List;

public interface ItemReviewAndRatingService {
    List<ItemReviewAndRating> getAllReviewsAndRatings();

    List<Item> getTopXItemsByRatings(int x);

    Item getTopRatedItem();

    Double getAverageRatingOfItem(int item_id);

    ItemReviewAndRating createReviewAndRating(ItemReviewAndRating itemReviewAndRating, int userId, int itemId);

    ItemReviewAndRating updateReviewAndRating(ItemReviewAndRating itemReviewAndRating, int userId, int itemId);

    void deleteItemReviewAndRating(int id);
}
