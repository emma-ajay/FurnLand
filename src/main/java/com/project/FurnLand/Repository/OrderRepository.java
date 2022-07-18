package com.project.FurnLand.Repository;


import com.project.FurnLand.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT u FROM Order u WHERE u.id = ?1 ")
    List<Order> getOrdersToVendor(Long vendorId);
}
