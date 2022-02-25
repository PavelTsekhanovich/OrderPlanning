package com.itrexgroup.orderplanning.persistence.repository;

import com.itrexgroup.orderplanning.persistence.entity.CustomerWarehouseDistance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerWarehouseDistanceRepository extends JpaRepository<CustomerWarehouseDistance, Long> {

    @Query(value = "select cwd from CustomerWarehouseDistance cwd join cwd.warehouse.products p " +
      "where cwd.customer.id = ?1 and p.id = ?2 order by cwd.distance")
    Page<CustomerWarehouseDistance> findByCustomerIdAndProductId(Long customerId, Long productId, Pageable pageable);

}
