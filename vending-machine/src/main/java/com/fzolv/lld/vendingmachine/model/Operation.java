package com.fzolv.lld.vendingmachine.model;

import org.springframework.http.ResponseEntity;

public interface Operation {

    static ResponseEntity<OperationBody> APPROVED(final Double amount) {
        return ResponseEntity.status(200).body(new OperationBody() {{
            this.status = State.PASSED;
            this.setMessage("Transaction Successful. Returning balance amt: %.2f".formatted(amount));
            this.setCode(200);
            this.setBalance(amount);
        }});
    }

    ResponseEntity<OperationBody> INSUFFICIENT_MONEY = ResponseEntity.status(400).body(new OperationBody() {{
        this.status = State.FAILED;
        this.message = "Insufficient Money Inserted";
        this.code = 400;
    }});

    ResponseEntity<OperationBody> INSUFFICIENT_CHANGE = ResponseEntity.status(400).body(new OperationBody() {{
        this.status = State.FAILED;
        this.message = "Change not available for current operation";
        this.code = 400;
    }});

    ResponseEntity<OperationBody> ITEM_UNAVAILABLE = ResponseEntity.status(400).body(new OperationBody() {{
        this.status = State.FAILED;
        this.message = "Item not available";
        this.code = 400;
    }});

    ResponseEntity<OperationBody> MACHINE_OUT_OF_ORDER = ResponseEntity.status(400).body(new OperationBody() {{
        this.status = State.FAILED;
        this.message = "Machine Out of Order";
        this.code = 400;
    }});

    ResponseEntity<OperationBody> TRANSACTION_EXISTS = ResponseEntity.status(400).body(new OperationBody() {{
        this.status = State.FAILED;
        this.message = "Transaction Already Exists";
        this.code = 400;
    }});

    static ResponseEntity<OperationBody> INVALID_ARGS(String msg) {
        return ResponseEntity.status(400).body(new OperationBody() {{
            this.status = State.FAILED;
            this.message = msg;
            this.code = 400;
        }});
    }
}
