package com.fzolv.lld.vendingmachine.exception;

import com.fzolv.lld.vendingmachine.model.Operation;
import com.fzolv.lld.vendingmachine.model.OperationBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<OperationBody> handleException(MethodArgumentNotValidException e) {
        return Operation.INVALID_ARGS(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(InsufficientMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<OperationBody> handleException(InsufficientMoneyException e) {
        return Operation.INSUFFICIENT_MONEY;
    }

    @ExceptionHandler(ItemUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<OperationBody> handleException(ItemUnavailableException e) {
        return Operation.ITEM_UNAVAILABLE;
    }

    @ExceptionHandler(MachineOFOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<OperationBody> handleException(MachineOFOException e) {
        return Operation.MACHINE_OUT_OF_ORDER;
    }

    @ExceptionHandler(TransactionExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<OperationBody> handleException(TransactionExistsException e) {
        return Operation.TRANSACTION_EXISTS;
    }

}
