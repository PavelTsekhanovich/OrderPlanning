package com.itrexgroup.orderplanning.api.dto.response;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CreatedCustomerDto {

    Long id;
    String firstName;
    String lastName;
    Double latitude;
    Double longitude;

}
