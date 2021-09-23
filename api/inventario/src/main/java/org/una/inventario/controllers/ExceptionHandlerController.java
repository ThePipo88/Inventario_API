package org.una.inventario.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.exceptions.MissingInputsException;
import org.una.inventario.exceptions.InformationNotSavedException;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.exceptions.NotImplementedException;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(MissingInputsException.class)
    public ResponseEntity<?> handleMissingInputsException(MissingInputsException emptyInputException) {
        return new ResponseEntity<>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }

    @ExceptionHandler(InformationNotSavedException.class)
    public ResponseEntity<?> handleInformationNotSavedException(InformationNotSavedException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getErrorMessage(), noSuchElementException.getErrorCode());
    }

    @ExceptionHandler(NotFoundInformationException.class)
    public ResponseEntity<?> handleNoFoundInformationException(NotFoundInformationException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getErrorMessage(), noSuchElementException.getErrorCode());
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<?> handleNotImplementedException(NotImplementedException noSuchElementException) {
        return new ResponseEntity<>(noSuchElementException.getErrorMessage(), noSuchElementException.getErrorCode());
    }
}
