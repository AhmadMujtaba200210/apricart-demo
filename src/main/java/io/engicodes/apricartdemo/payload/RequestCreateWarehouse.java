package io.engicodes.apricartdemo.payload;



public record RequestCreateWarehouse(
        String name,
        String location,
        Integer capacity,
        Integer availableCapacity
) {
}
