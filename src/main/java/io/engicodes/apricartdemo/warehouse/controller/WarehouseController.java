package io.engicodes.apricartdemo.warehouse.controller;

import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.payload.RequestCreateWarehouse;
import io.engicodes.apricartdemo.payload.RequestUpdateWarehouse;
import io.engicodes.apricartdemo.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling warehouse-related HTTP requests.
 */
@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private final WarehouseService service;

    /**
     * Constructor injection of WarehouseService.
     * @param service WarehouseService instance.
     */
    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    /**
     * Endpoint to create a new warehouse.
     * @param createWarehouse The request containing warehouse details.
     * @return ResponseEntity indicating success.
     * @throws ResourceNotFoundException if the warehouse already exists.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createWarehouse(@RequestBody RequestCreateWarehouse createWarehouse)
            throws ResourceNotFoundException {
        service.createWarehouse(createWarehouse);
        return ResponseEntity.ok("New Warehouse has been created!");
    }

    /**
     * Endpoint to update an existing warehouse.
     * @param warehouseId The ID of the warehouse to update.
     * @param updateWarehouse The request containing updated warehouse details.
     * @return ResponseEntity indicating success.
     * @throws Exception if the warehouse does not exist or the update fails.
     */
    @PutMapping("/update/{warehouseId}")
    public ResponseEntity<?> updateWarehouse(@PathVariable("warehouseId") Integer warehouseId,
                                             @RequestBody RequestUpdateWarehouse updateWarehouse) throws Exception {
        service.updateWarehouse(updateWarehouse, warehouseId);
        return ResponseEntity.ok("Warehouse has been updated!");
    }

    /**
     * Endpoint to delete a warehouse by its ID.
     * @param warehouseId The ID of the warehouse to delete.
     * @return ResponseEntity indicating success.
     * @throws ResourceNotFoundException if the warehouse does not exist.
     */
    @DeleteMapping("/delete/{warehouseId}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable("warehouseId") Integer warehouseId)
            throws ResourceNotFoundException {
        service.deleteWarehouse(warehouseId);
        return ResponseEntity.ok("Warehouse has been deleted!");
    }

    /**
     * Endpoint to retrieve a warehouse by its ID.
     * @param warehouseId The ID of the warehouse to retrieve.
     * @return ResponseEntity containing the warehouse.
     * @throws ResourceNotFoundException if the warehouse does not exist.
     */
    @GetMapping("/{warehouseId}")
    public ResponseEntity<?> getWarehouse(@PathVariable("warehouseId") Integer warehouseId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(service.getWarehouseById(warehouseId));
    }

    /**
     * Endpoint to retrieve all warehouses.
     * @return ResponseEntity containing the list of warehouses.
     * @throws Exception if no warehouses exist.
     */
    @GetMapping
    public ResponseEntity<?> getWarehouses() throws Exception {
        return ResponseEntity.ok(service.getAllWarehouse());
    }
}
