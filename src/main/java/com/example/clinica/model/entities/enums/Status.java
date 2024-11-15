package com.example.clinica.model.entities.enums;

public enum Status {
    WAITING(1),
    CANCELLED(2),
    DONE(3);
    private int value;

    private Status(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static Status valueOf(int value) {
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
