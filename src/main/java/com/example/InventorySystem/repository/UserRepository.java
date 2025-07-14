package com.example.InventorySystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.InventorySystem.Controller.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

