package com.endurance.emdb.Service;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemReviewAndRating;
import com.endurance.emdb.Repository.ItemRepository;
import com.endurance.emdb.Repository.ItemReviewAndRatingRepository;
import com.endurance.emdb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.reducing;

@Service
public class ItemReviewAndRatingServiceBean implements ItemReviewAndRatingService{

    @Autowired
    private ItemReviewAndRatingRepository itemReviewAndRatingRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ItemReviewAndRating> getAllReviewsAndRatings() {
        return itemReviewAndRatingRepository.findAllReviewsAndRating();
    }

    @Override
    public List<Item> getTopXItemsByRatings(int x) {
        List<ItemReviewAndRating> itemReviewAndRatingList = itemReviewAndRatingRepository.findAllReviewsAndRating();

        List<Item> finalList = new ArrayList<Item>();
        itemReviewAndRatingList.stream()
                .collect(Collectors.groupingBy(
                        ItemReviewAndRating::getItem, averagingInt(ItemReviewAndRating::getRating)
                        )
                )
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(x)
                .forEach(entry -> finalList.add(entry.getKey()));

        return finalList;
    }

    @Override
    public Item getTopRatedItem() {
        List<ItemReviewAndRating> itemReviewAndRatingList = itemReviewAndRatingRepository.findAllReviewsAndRating();

        return itemReviewAndRatingList.stream()
                .collect(Collectors.groupingBy(
                        ItemReviewAndRating::getItem, averagingInt(ItemReviewAndRating::getRating)
                    )
                )
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }

    @Override
    public Double getAverageRatingOfItem(int item_id) {
        List<ItemReviewAndRating> itemReviewAndRatingList = itemReviewAndRatingRepository.findAllReviewsAndRating();

        return itemReviewAndRatingList.stream()
                .filter(ratings -> ratings.getItem().getId() == item_id)
                .collect(Collectors.averagingInt(ItemReviewAndRating::getRating));
    }

    @Override
    public ItemReviewAndRating createReviewAndRating(ItemReviewAndRating itemReviewAndRating, int userId, int itemId) {
        if (itemReviewAndRating.getId() != 0){
            return null;
        }
        ItemReviewAndRating itemReviewAndRatingToCreate = new ItemReviewAndRating();
        itemReviewAndRatingToCreate.setItem(itemRepository.findOne(itemId));
        itemReviewAndRatingToCreate.setUser(userRepository.findOne(userId));
        itemReviewAndRatingToCreate.setRating(itemReviewAndRating.getRating());
        itemReviewAndRatingToCreate.setReview(itemReviewAndRating.getReview());
        itemReviewAndRatingToCreate = itemReviewAndRatingRepository.save(itemReviewAndRatingToCreate);
        return itemReviewAndRatingToCreate;
    }

    @Override
    public ItemReviewAndRating updateReviewAndRating(ItemReviewAndRating itemReviewAndRating, int userId, int itemId) {
        if (itemReviewAndRating.getId() == 0){
            return null;
        }
        ItemReviewAndRating itemReviewAndRatingToUpdate = itemReviewAndRatingRepository.findOne(itemReviewAndRating.getId());
        itemReviewAndRatingToUpdate.setItem(itemRepository.findOne(itemId));
        itemReviewAndRatingToUpdate.setUser(userRepository.findOne(userId));
        itemReviewAndRatingToUpdate.setRating(itemReviewAndRating.getRating());
        itemReviewAndRatingToUpdate.setReview(itemReviewAndRating.getReview());
        itemReviewAndRatingToUpdate = itemReviewAndRatingRepository.save(itemReviewAndRatingToUpdate);
        return itemReviewAndRatingToUpdate;
    }

    @Override
    public void deleteItemReviewAndRating(int id) {
        itemReviewAndRatingRepository.delete(id);
    }
}
