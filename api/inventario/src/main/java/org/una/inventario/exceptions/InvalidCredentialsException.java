package org.una.inventario.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Component
public class InvalidCredentialsException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final HttpStatus errorCode= HttpStatus.NOT_IMPLEMENTED;

    private final  String errorMessage= "Esta petición no ha sido implementada, contacte a soporte";

}
