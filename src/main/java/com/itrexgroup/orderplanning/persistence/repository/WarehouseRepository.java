package com.itrexgroup.orderplanning.persistence.repository;

import com.itrexgroup.orderplanning.persistence.entity.Warehouse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

/*select * from customers c
join customer_warehouse_distance d on c.id = d.customer_id
where c.id = 3 and d.warehouse_id in (select wp.warehouse_id from warehouse_product wp where wp.product_id = 1 )
order by d.distance
limit 1*/

    @Query(value = "select w.* from customers c " +
      "join customer_warehouse_distance d on c.id = d.customer_id " +
      "join warehouse w on w.id = d.warehouse_id " +
      "where c.id = :customerId and d.warehouse_id in (select wp.warehouse_id from warehouse_product wp where wp.product_id = :productId) " +
      "order by d.distance " +
      "limit 1", nativeQuery = true)
    Optional<Warehouse> findNearestBy(@Param("customerId") Long customerId, @Param("productId") Long productId);

}
