package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.SelectedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectedItemRepository extends JpaRepository<SelectedItem, Long > {
    @Query("SELECT u FROM SelectedItem u where u.cartId =?1")
    List<SelectedItem> itemsInCart(Long cartId);

}
