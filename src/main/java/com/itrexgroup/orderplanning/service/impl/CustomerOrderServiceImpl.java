package com.itrexgroup.orderplanning.service.impl;

import com.itrexgroup.orderplanning.exception.NotFoundException;
import com.itrexgroup.orderplanning.persistence.entity.CustomerOrder;
import com.itrexgroup.orderplanning.persistence.entity.OrderStatus;
import com.itrexgroup.orderplanning.persistence.repository.CustomerOrderRepository;
import com.itrexgroup.orderplanning.persistence.repository.CustomerRepository;
import com.itrexgroup.orderplanning.persistence.repository.CustomerWarehouseDistanceRepository;
import com.itrexgroup.orderplanning.persistence.repository.ProductRepository;
import com.itrexgroup.orderplanning.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer id: %d not found in the system, please create.";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product id: %d not found in warehouses.";

    private final CustomerWarehouseDistanceRepository customerWarehouseDistanceRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CustomerOrder createOrder(Long customerId, Long productId) {
        if (!customerRepository.existsById(customerId)) {
            throw new NotFoundException(String.format(CUSTOMER_NOT_FOUND_MESSAGE, customerId));
        }

        var customerWarehouseDistancePage = customerWarehouseDistanceRepository
          .findByCustomerIdAndProductId(customerId, productId, PageRequest.of(0, 1)); // page for limit 1

        if (customerWarehouseDistancePage.isEmpty()) {
            throw new NotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, productId));
        }

        var customerWarehouseDistance = customerWarehouseDistancePage.iterator().next();

        var customerOrder = CustomerOrder.builder()
          .customer(customerWarehouseDistance.getCustomer())
          .warehouse(customerWarehouseDistance.getWarehouse())
          .product(productRepository.getById(productId))
          .customerWarehouseDistance(customerWarehouseDistance)
          .status(OrderStatus.CREATED)
          .build();

        return customerOrderRepository.save(customerOrder);
    }

}
