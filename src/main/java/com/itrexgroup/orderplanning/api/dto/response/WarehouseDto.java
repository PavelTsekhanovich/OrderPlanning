package com.itrexgroup.orderplanning.api.dto.response;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class WarehouseDto {

    String name;
    Double latitude;
    Double longitude;

}
