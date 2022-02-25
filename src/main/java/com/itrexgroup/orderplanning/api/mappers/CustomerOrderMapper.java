package com.itrexgroup.orderplanning.api.mappers;

import com.itrexgroup.orderplanning.api.dto.response.CreatedOrderDto;
import com.itrexgroup.orderplanning.persistence.entity.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, WarehouseMapper.class})
public interface CustomerOrderMapper {

    @Mapping(source = "source.id", target = "orderId")
    @Mapping(source = "source.customerWarehouseDistance.distance", target = "distanceMeters")
    CreatedOrderDto from(CustomerOrder source);

}
