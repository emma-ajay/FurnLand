package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.Role;
import com.project.FurnLand.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional <Role> findByName(RoleName roleName);
}
