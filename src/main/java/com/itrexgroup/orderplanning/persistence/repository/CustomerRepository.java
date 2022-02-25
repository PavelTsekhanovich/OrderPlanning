package com.itrexgroup.orderplanning.persistence.repository;

import com.itrexgroup.orderplanning.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
