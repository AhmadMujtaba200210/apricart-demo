package io.engicodes.apricartdemo.warehouse.service;

import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.payload.RequestCreateWarehouse;
import io.engicodes.apricartdemo.payload.RequestUpdateWarehouse;
import io.engicodes.apricartdemo.warehouse.dao.WarehouseDao;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service layer for handling warehouse-related operations.
 */
@Service
public class WarehouseService {
    private final WarehouseDao warehouseDao;

    /**
     * Constructor injection of WarehouseDao.
     * @param warehouseDao WarehouseDao instance.
     */
    public WarehouseService(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    /**
     * Creates a new warehouse.
     * @param createWarehouse The request containing warehouse details.
     * @throws ResourceNotFoundException if a warehouse with the same name already exists.
     */
    public void createWarehouse(RequestCreateWarehouse createWarehouse) throws ResourceNotFoundException {
        Warehouse warehouseCheck = warehouseDao.getWareHouseByName(createWarehouse.name());
        if (warehouseCheck != null) throw new ResourceNotFoundException("Warehouse with same name already exists!");
        Warehouse warehouse = new Warehouse(
                createWarehouse.name(),
                createWarehouse.location(),
                createWarehouse.capacity(),
                createWarehouse.availableCapacity()
        );
        warehouseDao.createWareHouse(warehouse);
    }

    /**
     * Updates an existing warehouse.
     * @param updateWarehouse The request containing updated warehouse details.
     * @param warehouseId The ID of the warehouse to update.
     * @throws Exception if the warehouse does not exist or the update fails.
     */
    public void updateWarehouse(RequestUpdateWarehouse updateWarehouse, Integer warehouseId) throws Exception {
        Warehouse warehouseCheck = warehouseDao.getWareHouseById(warehouseId);
        boolean flag = false;
        if (warehouseCheck == null) throw new ResourceNotFoundException("Warehouse does not exist!");
        if (warehouseCheck.getWarehouseName().equals(updateWarehouse.name())) {
            warehouseCheck.setWarehouseName(updateWarehouse.name());
            flag = true;
        }
        if (warehouseCheck.getLocation().equals(updateWarehouse.location())) {
            warehouseCheck.setWarehouseName(updateWarehouse.name());
            flag = true;

        }
        if (warehouseCheck.getCapacity().equals(updateWarehouse.capacity())) {
            warehouseCheck.setCapacity(updateWarehouse.capacity());
            flag = true;

        }
        if (warehouseCheck.getAvaliableCapacity().equals(updateWarehouse.availableCapacity())) {
            warehouseCheck.setAvaliableCapacity(updateWarehouse.availableCapacity());
            flag = true;

        }
        if (!flag) {
            throw new Exception("Already updated!");
        }
        warehouseDao.updateWareHouse(warehouseCheck);
    }

    /**
     * Deletes a warehouse by its ID.
     * @param warehouseId The ID of the warehouse to delete.
     * @throws ResourceNotFoundException if the warehouse does not exist.
     */
    public void deleteWarehouse(Integer warehouseId) throws ResourceNotFoundException {
        Warehouse warehouseCheck = warehouseDao.getWareHouseById(warehouseId);
        if (warehouseCheck == null) throw new ResourceNotFoundException("Warehouse does not exist!");
        warehouseDao.deleteWareHouse(warehouseCheck);
    }

    /**
     * Retrieves a warehouse by its ID.
     * @param warehouseId The ID of the warehouse to retrieve.
     * @return The warehouse.
     * @throws ResourceNotFoundException if the warehouse does not exist.
     */
    public Object getWarehouseById(Integer warehouseId) throws ResourceNotFoundException {
        Warehouse warehouseCheck = warehouseDao.getWareHouseById(warehouseId);
        if (warehouseCheck == null) throw new ResourceNotFoundException("Warehouse does not exist!");
        return warehouseCheck;
    }

    /**
     * Retrieves all warehouses.
     * @return The list of warehouses.
     * @throws Exception if no warehouses exist.
     */
    public Object getAllWarehouse() throws Exception {
        List<Object> allWarehouse = Collections.singletonList(warehouseDao.getAllWareHouse());
        if (allWarehouse.get(0) == null) throw new Exception("No warehouse exists yet!");
        return allWarehouse;
    }
}
