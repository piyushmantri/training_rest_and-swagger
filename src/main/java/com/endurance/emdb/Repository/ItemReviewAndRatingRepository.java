package com.endurance.emdb.Repository;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemReviewAndRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemReviewAndRatingRepository extends CrudRepository<ItemReviewAndRating, Integer> {

    @Query("SELECT irr FROM ItemReviewAndRating irr")
    List<ItemReviewAndRating> findAllReviewsAndRating();

}
