package io.engicodes.apricartdemo.payload;

public record RequestUpdateWarehouse(
        String name,
        String location,
        Integer capacity,
        Integer availableCapacity
) {
}
