package com.jardim.exception;

public class RecordNotFoundException extends RuntimeException {
    
    public RecordNotFoundException( Long id) {
        super("Registro não localizado " + id);
    }
}
