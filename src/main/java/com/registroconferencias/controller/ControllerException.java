package com.registroconferencias.controller;

import com.registroconferencias.dto.ExceptionResponse;
import com.registroconferencias.exceptions.DateNotValidException;
import com.registroconferencias.exceptions.EmptyResultException;
import com.registroconferencias.exceptions.SessionSoldOutException;
import com.registroconferencias.exceptions.UserNotActiveException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ExceptionResponse response = new ExceptionResponse(
                "falla de validadción para uno o más campos",
                HttpStatus.BAD_REQUEST);

        ex.getFieldErrors().forEach(error ->
                    response.addError(error.getField(), error.getDefaultMessage())
        );

        return response;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ExceptionResponse handleEntityNotFoundException(EntityNotFoundException ex) {

        ExceptionResponse response = new ExceptionResponse(
                "el recurso solicitado no se encontro",
                HttpStatus.NOT_FOUND);

        response.addError("origen", ex.getMessage());

        return response;
    }

    @ExceptionHandler(DateNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ExceptionResponse handleIllegalArgumentException(DateNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse(
                "error en validación",
                HttpStatus.UNPROCESSABLE_ENTITY
        );

        response.addError("validacion", ex.getMessage());

        return response;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleNullPointerException(NullPointerException ex) {
        ExceptionResponse response = new ExceptionResponse(
                "error en validación",
                HttpStatus.BAD_REQUEST
        );

        response.addError("validacion", ex.getMessage());

        return response;
    }

    @ExceptionHandler(EmptyResultException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void handleEmptyResultException() {}

    @ExceptionHandler(SessionSoldOutException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ExceptionResponse handleSessionSoldOutException(SessionSoldOutException ex) {
        ExceptionResponse response = new ExceptionResponse(
                "limite del recurso alcanzado",
                HttpStatus.CONFLICT
        );

        response.addError("detalle", ex.getMessage());

        return response;
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ExceptionResponse handleEntityExistsException(EntityExistsException ex) {
        ExceptionResponse response = new ExceptionResponse(
                "el recurso ya existe",
                HttpStatus.CONFLICT
        );

        response.addError("recurso", ex.getMessage());

        return response;
    }

    @ExceptionHandler(UserNotActiveException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionResponse handleUserNotActiveException(UserNotActiveException ex) {
        ExceptionResponse response = new ExceptionResponse(
                "recurso no activo",
                HttpStatus.FORBIDDEN
        );

        response.addError("detalle", ex.getMessage());

        return response;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionResponse handleAuthenticationExceptionResponse() {
        ExceptionResponse response = new ExceptionResponse(
                "fallo en autenticación",
                HttpStatus.UNAUTHORIZED
        );

        response.addError("credenciale", "las credenciales no son correctas");

        return response;
    }
}
