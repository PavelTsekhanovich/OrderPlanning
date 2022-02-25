package com.itrexgroup.orderplanning.persistence.repository;

import com.itrexgroup.orderplanning.persistence.entity.Warehouse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
