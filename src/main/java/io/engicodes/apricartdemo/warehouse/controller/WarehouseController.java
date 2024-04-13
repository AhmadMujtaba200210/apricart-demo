package io.engicodes.apricartdemo.warehouse.controller;

import io.engicodes.apricartdemo.exceptions.ResourceNotFoundException;
import io.engicodes.apricartdemo.payload.RequestCreateWarehouse;
import io.engicodes.apricartdemo.payload.RequestUpdateWarehouse;
import io.engicodes.apricartdemo.warehouse.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    private final WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWarehouse(
            @RequestBody RequestCreateWarehouse createWarehouse
    ) throws ResourceNotFoundException {
        service.createWarehouse(createWarehouse);
        return ResponseEntity.ok("New Warehouse has been created!");
    }
    @PutMapping("/update/{warehouseId}")
    public ResponseEntity<?> updateWarehouse(
            @PathVariable("warehouseId") Integer warehouseId,
            RequestUpdateWarehouse createWarehouse) throws Exception {
        service.updateWarehouse(createWarehouse,warehouseId);
        return ResponseEntity.ok("Warehouse has been updated!");
    }
    @DeleteMapping("/delete/{warehouseId}")
    public ResponseEntity<?> deleteWarehouse(
            @PathVariable("warehouseId") Integer warehouseId) throws ResourceNotFoundException {
        service.deleteWarehouse(warehouseId);
        return ResponseEntity.ok("New Warehouse has been created!");
    }
    @GetMapping("/{warehouseId}")
    public ResponseEntity<?> createWarehouse(
            @PathVariable("warehouseId") Integer warehouseId) throws ResourceNotFoundException {
        ;
        return ResponseEntity.ok(service.getWarehouseById(warehouseId));
    }
    @GetMapping
    public ResponseEntity<?> getWarehouse() throws Exception {
        return ResponseEntity.ok(service.getAllWarehouse());
    }
}
