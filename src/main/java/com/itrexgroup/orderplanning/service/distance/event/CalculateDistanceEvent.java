package com.itrexgroup.orderplanning.service.distance.event;

import com.itrexgroup.orderplanning.persistence.entity.Customer;
import com.itrexgroup.orderplanning.persistence.entity.Warehouse;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor(staticName = "of")
public class CalculateDistanceEvent {

    Customer customer;
    List<Warehouse> warehouses;

}
