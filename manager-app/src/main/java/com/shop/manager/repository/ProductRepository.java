package com.shop.manager.repository;

import com.shop.manager.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Integer productId);

    void deleteById(Integer id);
}
