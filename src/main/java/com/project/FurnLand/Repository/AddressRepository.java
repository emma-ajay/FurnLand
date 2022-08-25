package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query("SELECT u FROM Address u WHERE u.userId= ?1")
    List<Address> getAddressByUserId(Long userId);

    @Query("SELECT u FROM Address u WHERE u.userId= :userId AND u.isDefault = true")
    Address getUserDefaultAddress(@Param("userId") Long userId);
}
