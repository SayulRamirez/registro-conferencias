package com.registroconferencias.exceptions;

public class SessionSoldOutException extends RuntimeException {
    public SessionSoldOutException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
