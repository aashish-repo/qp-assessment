package com.qpassessment.repository;

import com.qpassessment.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem,Long> {

    Optional<GroceryItem> findByItemKey(String itemKey);
}
