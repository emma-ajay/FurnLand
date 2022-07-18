package com.project.FurnLand.Repository;


import com.project.FurnLand.Entity.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem,Long> {

}
