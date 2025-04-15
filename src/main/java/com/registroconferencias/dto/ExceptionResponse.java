package com.registroconferencias.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class ExceptionResponse {

    private String message;

    private Map<String, Object> errors;

    private String timestamp;

    private Integer code;

    public ExceptionResponse(String message, HttpStatus status) {
        this.message = message;
        this.errors = new LinkedHashMap<>();
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.code = status.value();
    }

    public void addError(String origen, String message) {
        this.errors.put(origen, message);
    }
}
