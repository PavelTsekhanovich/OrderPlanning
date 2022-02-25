package com.itrexgroup.orderplanning.api.mappers;

import com.itrexgroup.orderplanning.api.dto.request.CreateCustomerDto;
import com.itrexgroup.orderplanning.api.dto.response.CreatedCustomerDto;
import com.itrexgroup.orderplanning.persistence.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer to(CreateCustomerDto source);

    CreatedCustomerDto from(Customer source);

}
