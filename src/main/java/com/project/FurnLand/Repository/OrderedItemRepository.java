package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem,Long> {
    @Query("SELECT u FROM OrderedItem u  WHERE u.vendorId =?1")
    List<OrderedItem> getAllVendorOrders(Long vendorId);

    @Query("SELECT u From OrderedItem u WHERE u.orderedItemId = ?1")
    OrderedItem findOrderedItemById(Long id);


    @Modifying
    @Query("UPDATE OrderedItem u SET  u.hasBeenConfirmed = true WHERE u.orderedItemId =?1")
     void  confirmOrderedItem(Long id);

    @Modifying
    @Query("UPDATE OrderedItem u SET  u.hasBeenSent = true WHERE u.orderedItemId =?1")
    void  confirmSentOrderedItem(Long id);

    @Modifying
    @Query("UPDATE OrderedItem u SET  u.hasBeenDelivered = true WHERE u.orderedItemId =?1")
    void  confirmDeliveredOrderedItem(Long id);

}
