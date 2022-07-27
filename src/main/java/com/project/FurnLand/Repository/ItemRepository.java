package com.project.FurnLand.Repository;


import com.project.FurnLand.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository <Item,Long > {
    @Query("SELECT u FROM Item u where u.vendorId = ?1")
    List<Item> itemsByVendor(Long userID);

    @Query("SELECT u FROM Item u WHERE u.id =?1")
    Item findItemById (Long id);
}
