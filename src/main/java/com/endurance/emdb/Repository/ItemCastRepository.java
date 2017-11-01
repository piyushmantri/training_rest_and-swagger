package com.endurance.emdb.Repository;

import com.endurance.emdb.Model.Item;
import com.endurance.emdb.Model.ItemCast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemCastRepository extends CrudRepository<Item, Integer>{
    void save(ItemCast createdItemCast);

    @Query("SELECT i FROM Item i WHERE id IN (SELECT item.id FROM ItemCast WHERE cast LIKE ':cast')")
    List<Item> findItemsByCastName(@Param("cast")String cast);

    @Query("SELECT ic FROM ItemCast ic  WHERE item_id = :item_id")
    List<ItemCast> findItemCastByItemId(@Param("item_id")int item_id);

    @Query("SELECT ic FROM ItemCast ic")
    List<ItemCast> findAllItemCasts();

    @Query("SELECT ic FROM ItemCast ic WHERE id = :id")
    ItemCast findById(@Param("id") int id);
}
