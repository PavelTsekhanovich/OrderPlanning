package com.itrexgroup.orderplanning.api.dto.response;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CreatedOrderDto {

    Long orderId;
    Double distanceMeters;
    ProductDto product;
    WarehouseDto warehouse;

}
