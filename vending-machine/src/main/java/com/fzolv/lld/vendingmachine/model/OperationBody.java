package com.fzolv.lld.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationBody {

    State status;
    String message;
    Integer code;
    Double balance;
}

enum State {
    PASSED, FAILED
}