package com.example.InventorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InventorySystem.model.InventoryItem;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {
	
}

