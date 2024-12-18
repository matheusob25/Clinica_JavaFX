package com.example.clinica.model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

    private Map<String, String> errors = new HashMap<>();

    public ValidationException(String message) {
        super(message);
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void addError(String field, String message) {
        errors.put(field, message);
    }

}
