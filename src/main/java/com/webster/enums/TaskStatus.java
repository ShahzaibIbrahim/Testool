package com.webster.enums;

public enum TaskStatus {

    SUCCESS ("01", "Task Executed"), FAILURE ("02", "Task Failed"), PENDING("03", "Task Pending");

    private final String status;
    private final String message;

    TaskStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
