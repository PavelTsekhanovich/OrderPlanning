package com.itrexgroup.orderplanning.api.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
@Jacksonized
public class CreateCustomerDto {

    @NotBlank
    @NotNull
    @Length(max = 20)
    String firstName;

    @NotBlank
    @NotNull
    @Length(max = 20)
    String lastName;

    @NotNull
    Double latitude;

    @NotNull
    Double longitude;

}
