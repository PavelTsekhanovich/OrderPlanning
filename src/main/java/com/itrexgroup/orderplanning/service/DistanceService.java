package com.itrexgroup.orderplanning.service;

import com.itrexgroup.orderplanning.persistence.entity.Customer;
import com.itrexgroup.orderplanning.persistence.entity.Warehouse;
import java.util.List;

public interface DistanceService {

    void calculateDistance(Customer customer, List<Warehouse> warehouses);

}
