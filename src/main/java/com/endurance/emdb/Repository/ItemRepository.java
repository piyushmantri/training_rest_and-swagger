package com.endurance.emdb.Repository;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findItemsByType(String type);
}
