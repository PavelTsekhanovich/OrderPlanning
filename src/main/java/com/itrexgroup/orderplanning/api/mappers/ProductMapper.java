package com.itrexgroup.orderplanning.api.mappers;

import com.itrexgroup.orderplanning.api.dto.response.ProductDto;
import com.itrexgroup.orderplanning.persistence.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto from(Product product);

}
