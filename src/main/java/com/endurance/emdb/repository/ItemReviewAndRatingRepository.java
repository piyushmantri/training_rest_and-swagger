package com.endurance.emdb.repository;

import com.endurance.emdb.model.ItemReviewAndRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemReviewAndRatingRepository extends CrudRepository<ItemReviewAndRating, Integer> {

    @Query("SELECT irr FROM ItemReviewAndRating irr")
    List<ItemReviewAndRating> findAllReviewsAndRating();

}
