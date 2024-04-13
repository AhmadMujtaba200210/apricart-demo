package io.engicodes.apricartdemo.warehouse.service;

import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.payload.RequestCreateWarehouse;
import io.engicodes.apricartdemo.payload.RequestUpdateWarehouse;
import io.engicodes.apricartdemo.warehouse.dao.WarehouseDao;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseDao warehouseDao;

    public WarehouseService(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    public void createWarehouse(RequestCreateWarehouse createWarehouse) throws ResourceNotFoundException {
        Warehouse warehouseCheck = warehouseDao.getWareHouseByName(createWarehouse.name());
        if (warehouseCheck!=null) throw new ResourceNotFoundException("Warehouse with same name already exists!");
        Warehouse warehouse = new Warehouse(
                createWarehouse.name(),
                createWarehouse.location(),
                createWarehouse.capacity(),
                createWarehouse.availableCapacity()
        );
        warehouseDao.createWareHouse(warehouse);
    }

    public void updateWarehouse(RequestUpdateWarehouse updateWarehouse, Integer warehouseId) throws Exception {
        Warehouse warehouseCheck = warehouseDao.getWareHouseById(warehouseId);
        boolean flag=false;
        if (warehouseCheck==null) throw new ResourceNotFoundException("Warehouse does not exists!");
        if (warehouseCheck.getWarehouseName().equals(updateWarehouse.name())){
            warehouseCheck.setWarehouseName(updateWarehouse.name());
            flag=true;
        }
        if (warehouseCheck.getLocation().equals(updateWarehouse.location())){
            warehouseCheck.setWarehouseName(updateWarehouse.name());
            flag=true;

        }
        if (warehouseCheck.getCapacity().equals(updateWarehouse.capacity())){
            warehouseCheck.setCapacity(updateWarehouse.capacity());
            flag=true;

        }
        if (warehouseCheck.getAvaliableCapacity().equals(updateWarehouse.availableCapacity())){
            warehouseCheck.setAvaliableCapacity(updateWarehouse.availableCapacity());
            flag=true;

        }
        if (!flag){
            throw new Exception("Already updated!");
        }
        warehouseDao.updateWareHouse(warehouseCheck);
    }

    public void deleteWarehouse(Integer warehouseId) throws ResourceNotFoundException {
        Warehouse warehouseCheck = warehouseDao.getWareHouseById(warehouseId);
        if (warehouseCheck==null) throw new ResourceNotFoundException("Warehouse does not exists!");
        warehouseDao.deleteWareHouse(warehouseCheck);
    }

    public Object getWarehouseById(Integer warehouseId) throws ResourceNotFoundException {
        Warehouse warehouseCheck = warehouseDao.getWareHouseById(warehouseId);
        if (warehouseCheck==null) throw new ResourceNotFoundException("Warehouse does not exists!");
        return warehouseCheck;
    }

    public Object getAllWarehouse() throws Exception {
        List<Object> allWarehouse = Collections.singletonList(warehouseDao.getAllWareHouse());
        if (allWarehouse.get(0)==null) throw new Exception("No warehouse exists till yet!");
        return allWarehouse;
    }
}
