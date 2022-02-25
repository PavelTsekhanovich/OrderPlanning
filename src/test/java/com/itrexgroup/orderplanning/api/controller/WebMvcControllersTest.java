package com.itrexgroup.orderplanning.api.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itrexgroup.orderplanning.api.dto.request.CreateCustomerDto;
import com.itrexgroup.orderplanning.api.dto.request.CreateOrderDto;
import com.itrexgroup.orderplanning.api.dto.response.CreatedCustomerDto;
import com.itrexgroup.orderplanning.api.dto.response.CreatedOrderDto;
import com.itrexgroup.orderplanning.persistence.entity.Customer;
import com.itrexgroup.orderplanning.persistence.repository.CustomerRepository;
import com.itrexgroup.orderplanning.persistence.repository.ProductRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class WebMvcControllersTest {

    private static final String CUSTOMER_BASE_URL = "/api/customer";
    private static final String ORDER_BASE_URL = "/api/order";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createCustomer_shouldCreateCustomer() throws Exception {
        // given
        var createCustomerDto = createCustomerDto();

        // when
        var mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post(CUSTOMER_BASE_URL)
              .accept(APPLICATION_JSON_VALUE)
              .contentType(APPLICATION_JSON_VALUE)
              .content(mapper.writeValueAsString(createCustomerDto)))
          .andReturn();

        // then
        var response = mvcResult.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertNotNull(response.getContentAsString());

        var createdCustomer = mapper.readValue(response.getContentAsString(), CreatedCustomerDto.class);

        assertNotNull(createdCustomer.getId());
        assertNotNull(createdCustomer.getFirstName());
        assertNotNull(createdCustomer.getLastName());
        assertNotNull(createdCustomer.getLongitude());
        assertNotNull(createdCustomer.getLatitude());
    }

    @Test
    void createCustomerOrder_shouldCreateCustomerOrder() throws Exception {
        // given
        var product = productRepository.findById(2L).orElseThrow();

        var createCustomerDto = createCustomerDto();

        var createCustomer = mockMvc.perform(
            MockMvcRequestBuilders.post(CUSTOMER_BASE_URL)
              .accept(APPLICATION_JSON_VALUE)
              .contentType(APPLICATION_JSON_VALUE)
              .content(mapper.writeValueAsString(createCustomerDto)))
          .andReturn();

        var createdCustomer = mapper.readValue(createCustomer.getResponse().getContentAsString(), CreatedCustomerDto.class);

        var createOrder = CreateOrderDto.builder()
          .customerId(createdCustomer.getId())
          .productId(product.getId())
          .build();

        // when
        var mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post(ORDER_BASE_URL)
              .accept(APPLICATION_JSON_VALUE)
              .contentType(APPLICATION_JSON_VALUE)
              .content(mapper.writeValueAsString(createOrder)))
          .andReturn();

        // then
        var response = mvcResult.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertNotNull(response.getContentAsString());

        var createdOrder = mapper.readValue(response.getContentAsString(), CreatedOrderDto.class);

        assertNotNull(createdOrder.getOrderId());
        assertNotNull(createdOrder.getDistanceMeters());
        assertNotNull(createdOrder.getProduct());
        assertEquals(product.getId(), createdOrder.getProduct().getId());
        assertNotNull(createdOrder.getWarehouse());
        assertNotNull(createdOrder.getWarehouse().getLatitude());
        assertNotNull(createdOrder.getWarehouse().getLongitude());
    }

    @Test
    void createCustomerOrder_productNotFound() throws Exception {
        // given
        var customer = Customer.builder()
          .firstName(RandomStringUtils.randomAlphanumeric(10))
          .lastName(RandomStringUtils.randomAlphanumeric(10))
          .latitude(1D)
          .longitude(1D)
          .build();

        var createdCustomer = customerRepository.save(customer);

        var createOrder = CreateOrderDto.builder()
          .customerId(createdCustomer.getId())
          .productId(0L)
          .build();

        // when
        var mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post(ORDER_BASE_URL)
              .accept(APPLICATION_JSON_VALUE)
              .contentType(APPLICATION_JSON_VALUE)
              .content(mapper.writeValueAsString(createOrder)))
          .andReturn();

        // then
        var response = mvcResult.getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    private CreateCustomerDto createCustomerDto() {
        return CreateCustomerDto.builder()
          .firstName(RandomStringUtils.randomAlphanumeric(10))
          .lastName(RandomStringUtils.randomAlphanumeric(10))
          .latitude(1D)
          .longitude(1D)
          .build();
    }

}