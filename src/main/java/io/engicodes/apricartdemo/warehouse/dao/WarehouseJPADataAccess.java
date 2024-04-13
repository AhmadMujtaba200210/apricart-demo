package io.engicodes.apricartdemo.warehouse.dao;

import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("jpa-warehouse")
@Transactional
public class WarehouseJPADataAccess implements WarehouseDao
{
    private final WarehouseRepository warehouseRepository;

    public WarehouseJPADataAccess(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void createWareHouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Override
    public void updateWareHouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWareHouse(Warehouse warehouse) {
        warehouseRepository.delete(warehouse);
    }

    @Override
    public Warehouse getWareHouseById(Integer warehouseId) {
        return warehouseRepository.getWarehouseByWarehouseId(warehouseId);
    }

    @Override
    public Warehouse getWareHouseByName(String name) {
        return warehouseRepository.getWarehouseByWarehouseName(name);
    }

    @Override
    public List<Warehouse> getAllWareHouse() {
        return warehouseRepository.findAll();
    }
}
