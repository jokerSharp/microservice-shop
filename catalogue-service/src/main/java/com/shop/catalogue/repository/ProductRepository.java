package com.shop.catalogue.repository;

import com.shop.catalogue.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);

    @Query(name = "Product.findAllByTitleAndDetailsLikeIgnoreCase")
    Iterable<Product> findAllByTitleAndDetailsLikeIgnoreCase(String title, String details);
}
