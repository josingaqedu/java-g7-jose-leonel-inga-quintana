package com.codigo.jose.inga.examen.exception;

public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
