package com.registroconferencias.exceptions;

public class EmptyResultException extends RuntimeException {
    public EmptyResultException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
