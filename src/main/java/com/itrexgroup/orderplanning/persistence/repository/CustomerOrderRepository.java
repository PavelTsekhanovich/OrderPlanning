package com.itrexgroup.orderplanning.persistence.repository;

import com.itrexgroup.orderplanning.persistence.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {


}
