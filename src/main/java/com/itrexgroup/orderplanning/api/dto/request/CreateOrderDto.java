package com.itrexgroup.orderplanning.api.dto.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CreateOrderDto {

    Long customerId;
    Long productId;

}
