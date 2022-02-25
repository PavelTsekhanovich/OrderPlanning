package com.itrexgroup.orderplanning.api.controller;

import com.itrexgroup.orderplanning.api.dto.response.ProductDto;
import com.itrexgroup.orderplanning.api.mappers.ProductMapper;
import com.itrexgroup.orderplanning.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        var products = productService.getAllProducts().stream()
          .map(productMapper::from)
          .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

}
