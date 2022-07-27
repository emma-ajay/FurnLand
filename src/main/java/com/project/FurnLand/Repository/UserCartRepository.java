package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart,Long> {
    @Query("SELECT u FROM UserCart u WHERE u.userId=?1")
    UserCart selectUserCart(Long userId);
}
