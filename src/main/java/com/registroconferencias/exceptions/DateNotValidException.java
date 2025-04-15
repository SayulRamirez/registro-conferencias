package com.registroconferencias.exceptions;

public class DateNotValidException extends RuntimeException {

    public DateNotValidException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
