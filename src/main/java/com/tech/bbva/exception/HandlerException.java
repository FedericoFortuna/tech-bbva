package com.tech.bbva.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {
    @Value(value = "No existe el cliente especificado.")
    private String clientNotFound;

    @Value(value = "No existe el servicio bancario especificado")
    private String bankServiceNotFound;


    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFoundException(ClientNotFoundException e){
        return new ResponseEntity<>(clientNotFound, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BankServiceNotFoundException.class)
    public ResponseEntity<?> droneIsNotLoadedException(BankServiceNotFoundException e){
        return new ResponseEntity<>(bankServiceNotFound, HttpStatus.BAD_REQUEST);
    }

}
