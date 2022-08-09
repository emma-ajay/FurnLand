package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.RoleName;
import com.project.FurnLand.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 ")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = ?1 ")
     User ByEmail(String email);

    @Query("SELECT u FROM User u  INNER JOIN  Role r ON r.id =?1 ")
    List<User> getUserByRole(Long id);


}
