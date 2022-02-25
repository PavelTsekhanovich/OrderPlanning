package com.itrexgroup.orderplanning.service;

import com.itrexgroup.orderplanning.persistence.entity.CustomerOrder;

public interface CustomerOrderService {

    CustomerOrder createOrder(Long customerId, Long productId);

}
