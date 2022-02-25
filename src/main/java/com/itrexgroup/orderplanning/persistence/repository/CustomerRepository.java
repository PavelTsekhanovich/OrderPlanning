package com.itrexgroup.orderplanning.persistence.repository;

import com.itrexgroup.orderplanning.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {




    /*select * from customers c
join distances d on c.id = d.customer_id

where c.id = 3 and d.warehouse_id in (select wp.warehouse_id from warehouse_product wp where wp.product_id = 1 )

order by d.distance*/


}
