package Enums;

public enum BackpackLevel {
    small(12),
    big(24),
    deluxe(20000);
    private final int size;
    BackpackLevel(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
}
