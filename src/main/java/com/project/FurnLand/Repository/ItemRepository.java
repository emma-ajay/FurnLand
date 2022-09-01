package com.project.FurnLand.Repository;


import com.project.FurnLand.Entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT u FROM Item u WHERE CONCAT(u.itemName, u.itemType, u.itemCategory, u.itemPrice) LIKE %?1%")
    List<Item> search(String keyword);

    @Query(value = "SELECT * FROM Items",nativeQuery = true)
    Page<Item> findAllItems (Pageable pageable);

    @Query("SELECT u FROM Item u WHERE CONCAT(u.itemName, u.itemType, u.itemCategory, u.itemPrice) LIKE %?1%")
    Page<Item> query(Pageable pageable, String keyword);
}
