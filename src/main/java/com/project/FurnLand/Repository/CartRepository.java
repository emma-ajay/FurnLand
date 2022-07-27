package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long> {

    @Query("SELECT u from Cart u WHERE u.id=?1 ")
    Cart getCartById(Long id);

}
