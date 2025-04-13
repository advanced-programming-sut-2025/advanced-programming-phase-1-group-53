package Enums;

public enum Quest {
    ;

    private boolean isAvailable;
    private final int during;
    private boolean isDone;

    Quest(boolean isAvailable, int during, boolean isDone) {
        this.isAvailable = isAvailable;
        this.during = during;
        this.isDone = isDone;
    }
}
