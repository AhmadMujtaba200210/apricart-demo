package io.engicodes.apricartdemo.warehouse.dao;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;


import java.util.List;

public interface WarehouseDao {
    void createWareHouse(Warehouse warehouse);
    void updateWareHouse(Warehouse updateWarehouse);
    void deleteWareHouse(Warehouse warehouse);
    Warehouse getWareHouseById(Integer warehouseId);
    Warehouse getWareHouseByName(String name);
    List<Warehouse> getAllWareHouse();
}
