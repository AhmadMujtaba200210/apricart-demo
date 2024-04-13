package io.engicodes.apricartdemo.warehouse.dao;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;



public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse getWarehouseByWarehouseId(Integer warehouseId);
    Warehouse getWarehouseByWarehouseName(String warehouseName);
}
