package agh.ics.oop.model;

public enum MoveDirection {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT;

    @Override
    public String toString() {
        return switch (this) {
            case FORWARD -> "Zwierzak idzie do przodu";
            case BACKWARD -> "Zwierzak idzie do tyłu";
            case RIGHT -> "Zwierzak skręca w prawo";
            case LEFT -> "Zwierzak skręca w lewo";
        };
    }
}
