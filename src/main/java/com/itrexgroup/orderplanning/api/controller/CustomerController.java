package com.itrexgroup.orderplanning.api.controller;

import com.itrexgroup.orderplanning.api.dto.request.CreateCustomerDto;
import com.itrexgroup.orderplanning.api.dto.response.CreatedCustomerDto;
import com.itrexgroup.orderplanning.api.mappers.CustomerMapper;
import com.itrexgroup.orderplanning.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CreatedCustomerDto> createCustomer(@RequestBody CreateCustomerDto createdCustomer) {
        var customer = customerService.createCustomer(customerMapper.to(createdCustomer));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.from(customer));
    }

}
