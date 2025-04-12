package com.registroconferencias.exceptions;

public class UserNotActive extends RuntimeException {

    public UserNotActive(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
