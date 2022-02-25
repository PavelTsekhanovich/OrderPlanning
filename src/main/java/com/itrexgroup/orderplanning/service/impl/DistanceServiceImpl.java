package com.itrexgroup.orderplanning.service.impl;

import com.itrexgroup.orderplanning.persistence.entity.Customer;
import com.itrexgroup.orderplanning.persistence.entity.CustomerWarehouseDistance;
import com.itrexgroup.orderplanning.persistence.entity.Warehouse;
import com.itrexgroup.orderplanning.persistence.repository.CustomerWarehouseDistanceRepository;
import com.itrexgroup.orderplanning.service.DistanceService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.util.SloppyMath;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DistanceServiceImpl implements DistanceService {

    private final CustomerWarehouseDistanceRepository distanceRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void calculateDistance(Customer customer, List<Warehouse> warehouses) {
        var distances = new ArrayList<CustomerWarehouseDistance>();

        warehouses.parallelStream().forEach(warehouse -> {
            var calculatedDistance = SloppyMath.haversinMeters(
              customer.getLatitude(), customer.getLongitude(),
              warehouse.getLatitude(), warehouse.getLongitude());

            distances.add(CustomerWarehouseDistance.builder()
              .customer(customer)
              .warehouse(warehouse)
              .distance(calculatedDistance)
              .build());
        });

        distanceRepository.saveAll(distances);
    }
}
