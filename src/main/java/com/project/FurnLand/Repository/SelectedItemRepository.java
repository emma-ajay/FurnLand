package com.project.FurnLand.Repository;

import com.project.FurnLand.Entity.SelectedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedItemRepository extends JpaRepository<SelectedItem, Long > {

}
