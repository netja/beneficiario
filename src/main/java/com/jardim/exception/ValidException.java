package com.jardim.exception;

public class ValidException extends RuntimeException {
    
    public ValidException( String msg) {
        super("Erro de validação : " + msg);
    }
}
