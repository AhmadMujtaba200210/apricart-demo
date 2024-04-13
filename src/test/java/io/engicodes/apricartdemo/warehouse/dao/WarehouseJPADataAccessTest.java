package io.engicodes.apricartdemo.warehouse.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.engicodes.apricartdemo.warehouse.model.Warehouse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WarehouseJPADataAccess.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class WarehouseJPADataAccessTest {
    @Autowired
    private WarehouseJPADataAccess warehouseJPADataAccess;

    @MockBean
    private WarehouseRepository warehouseRepository;

    /**
     * Method under test: {@link WarehouseJPADataAccess#createWareHouse(Warehouse)}
     */
    @Test
    void testCreateWareHouse() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setAvaliableCapacity(42);
        warehouse.setCapacity(3);
        warehouse.setLocation("Location");
        warehouse.setWarehouseId(1);
        warehouse.setWarehouseName("Warehouse Name");
        when(warehouseRepository.save(Mockito.<Warehouse>any())).thenReturn(warehouse);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setAvaliableCapacity(42);
        warehouse2.setCapacity(3);
        warehouse2.setLocation("Location");
        warehouse2.setWarehouseId(1);
        warehouse2.setWarehouseName("Warehouse Name");

        // Act
        warehouseJPADataAccess.createWareHouse(warehouse2);

        // Assert that nothing has changed
        verify(warehouseRepository).save(isA(Warehouse.class));
        assertEquals("Location", warehouse2.getLocation());
        assertEquals("Warehouse Name", warehouse2.getWarehouseName());
        assertEquals(1, warehouse2.getWarehouseId().intValue());
        assertEquals(3, warehouse2.getCapacity().intValue());
        assertEquals(42, warehouse2.getAvaliableCapacity().intValue());
    }

    /**
     * Method under test: {@link WarehouseJPADataAccess#updateWareHouse(Warehouse)}
     */
    @Test
    void testUpdateWareHouse() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setAvaliableCapacity(42);
        warehouse.setCapacity(3);
        warehouse.setLocation("Location");
        warehouse.setWarehouseId(1);
        warehouse.setWarehouseName("Warehouse Name");
        when(warehouseRepository.save(Mockito.<Warehouse>any())).thenReturn(warehouse);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setAvaliableCapacity(42);
        warehouse2.setCapacity(3);
        warehouse2.setLocation("Location");
        warehouse2.setWarehouseId(1);
        warehouse2.setWarehouseName("Warehouse Name");

        // Act
        warehouseJPADataAccess.updateWareHouse(warehouse2);

        // Assert that nothing has changed
        verify(warehouseRepository).save(isA(Warehouse.class));
        assertEquals("Location", warehouse2.getLocation());
        assertEquals("Warehouse Name", warehouse2.getWarehouseName());
        assertEquals(1, warehouse2.getWarehouseId().intValue());
        assertEquals(3, warehouse2.getCapacity().intValue());
        assertEquals(42, warehouse2.getAvaliableCapacity().intValue());
    }

    /**
     * Method under test: {@link WarehouseJPADataAccess#deleteWareHouse(Warehouse)}
     */
    @Test
    void testDeleteWareHouse() {
        // Arrange
        doNothing().when(warehouseRepository).delete(Mockito.<Warehouse>any());

        Warehouse warehouse = new Warehouse();
        warehouse.setAvaliableCapacity(42);
        warehouse.setCapacity(3);
        warehouse.setLocation("Location");
        warehouse.setWarehouseId(1);
        warehouse.setWarehouseName("Warehouse Name");

        // Act
        warehouseJPADataAccess.deleteWareHouse(warehouse);

        // Assert that nothing has changed
        verify(warehouseRepository).delete(isA(Warehouse.class));
        assertEquals("Location", warehouse.getLocation());
        assertEquals("Warehouse Name", warehouse.getWarehouseName());
        assertEquals(1, warehouse.getWarehouseId().intValue());
        assertEquals(3, warehouse.getCapacity().intValue());
        assertEquals(42, warehouse.getAvaliableCapacity().intValue());
    }

    /**
     * Method under test: {@link WarehouseJPADataAccess#getWareHouseById(Integer)}
     */
    @Test
    void testGetWareHouseById() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setAvaliableCapacity(42);
        warehouse.setCapacity(3);
        warehouse.setLocation("Location");
        warehouse.setWarehouseId(1);
        warehouse.setWarehouseName("Warehouse Name");
        when(warehouseRepository.getWarehouseByWarehouseId(Mockito.<Integer>any())).thenReturn(warehouse);

        // Act
        Warehouse actualWareHouseById = warehouseJPADataAccess.getWareHouseById(1);

        // Assert
        verify(warehouseRepository).getWarehouseByWarehouseId(eq(1));
        assertSame(warehouse, actualWareHouseById);
    }

    /**
     * Method under test: {@link WarehouseJPADataAccess#getWareHouseByName(String)}
     */
    @Test
    void testGetWareHouseByName() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setAvaliableCapacity(42);
        warehouse.setCapacity(3);
        warehouse.setLocation("Location");
        warehouse.setWarehouseId(1);
        warehouse.setWarehouseName("Warehouse Name");
        when(warehouseRepository.getWarehouseByWarehouseName(Mockito.<String>any())).thenReturn(warehouse);

        // Act
        Warehouse actualWareHouseByName = warehouseJPADataAccess.getWareHouseByName("Name");

        // Assert
        verify(warehouseRepository).getWarehouseByWarehouseName(eq("Name"));
        assertSame(warehouse, actualWareHouseByName);
    }

    /**
     * Method under test: {@link WarehouseJPADataAccess#getAllWareHouse()}
     */
    @Test
    void testGetAllWareHouse() {
        // Arrange
        ArrayList<Warehouse> warehouseList = new ArrayList<>();
        when(warehouseRepository.findAll()).thenReturn(warehouseList);

        // Act
        List<Warehouse> actualAllWareHouse = warehouseJPADataAccess.getAllWareHouse();

        // Assert
        verify(warehouseRepository).findAll();
        assertTrue(actualAllWareHouse.isEmpty());
        assertSame(warehouseList, actualAllWareHouse);
    }
}
