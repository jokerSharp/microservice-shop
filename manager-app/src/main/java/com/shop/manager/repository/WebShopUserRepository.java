package com.shop.manager.repository;

import com.shop.manager.entity.WebShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebShopUserRepository extends JpaRepository<WebShopUser, Integer> {

    Optional<WebShopUser> findByUsername(String username);
}
