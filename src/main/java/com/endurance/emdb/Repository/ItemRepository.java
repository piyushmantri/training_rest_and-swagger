package com.endurance.emdb.repository;

import com.endurance.emdb.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findItemsByType(String type);
}
