package com.itrexgroup.orderplanning.api.mappers;

import com.itrexgroup.orderplanning.api.dto.response.WarehouseDto;
import com.itrexgroup.orderplanning.persistence.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    WarehouseDto from(Warehouse warehouse);

}
