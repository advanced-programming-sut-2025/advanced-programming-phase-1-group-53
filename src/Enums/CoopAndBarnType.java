package Enums;

public enum CoopAndBarnType {
    normal(4),
    big(8),
    deluxe(12);
    private final int capacity;

    CoopAndBarnType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
