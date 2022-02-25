package com.itrexgroup.orderplanning.service.impl;

import com.itrexgroup.orderplanning.persistence.entity.Product;
import com.itrexgroup.orderplanning.persistence.repository.ProductRepository;
import com.itrexgroup.orderplanning.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
