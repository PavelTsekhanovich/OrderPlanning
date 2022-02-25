package com.itrexgroup.orderplanning.api.controller;

import com.itrexgroup.orderplanning.api.dto.request.CreateOrderDto;
import com.itrexgroup.orderplanning.api.dto.response.CreatedOrderDto;
import com.itrexgroup.orderplanning.api.mappers.CustomerOrderMapper;
import com.itrexgroup.orderplanning.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final CustomerOrderService customerOrderService;
    private final CustomerOrderMapper customerOrderMapper;

    @PostMapping
    public ResponseEntity<CreatedOrderDto> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        var createdOrder = customerOrderService.createOrder(
          createOrderDto.getCustomerId(),
          createOrderDto.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(customerOrderMapper.from(createdOrder));
    }

}
