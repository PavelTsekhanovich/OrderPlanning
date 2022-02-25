package com.itrexgroup.orderplanning.service.impl;

import com.itrexgroup.orderplanning.persistence.entity.Customer;
import com.itrexgroup.orderplanning.persistence.repository.CustomerRepository;
import com.itrexgroup.orderplanning.persistence.repository.WarehouseRepository;
import com.itrexgroup.orderplanning.service.CustomerService;
import com.itrexgroup.orderplanning.service.distance.event.CalculateDistanceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final WarehouseRepository warehouseRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public Customer createCustomer(Customer customer) {
        var customerCreated = customerRepository.save(customer);

        applicationEventPublisher.publishEvent(
          CalculateDistanceEvent.of(customerCreated, warehouseRepository.findAll()) //find all only for test, in real project we can use bulk operation and jobs
        );

        return customerCreated;
    }
}
