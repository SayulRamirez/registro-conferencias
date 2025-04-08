package com.registroconferencias.exceptions;

public class EntityNotSaveException extends RuntimeException {
    public EntityNotSaveException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
