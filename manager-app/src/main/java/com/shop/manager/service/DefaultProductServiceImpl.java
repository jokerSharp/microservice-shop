package com.shop.manager.service;

import com.shop.manager.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
}
