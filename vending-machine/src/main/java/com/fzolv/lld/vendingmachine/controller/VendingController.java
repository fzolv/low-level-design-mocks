package com.fzolv.lld.vendingmachine.controller;

import com.fzolv.lld.vendingmachine.model.Operation;
import com.fzolv.lld.vendingmachine.model.OperationBody;
import com.fzolv.lld.vendingmachine.model.Transaction;
import com.fzolv.lld.vendingmachine.service.VendingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendingController {

    @Autowired
    VendingService vendingService;

    @PostMapping("/vending")
    public ResponseEntity<OperationBody> vendOperation(@Valid @RequestBody Transaction transaction) {
        return Operation.APPROVED(vendingService.vend(transaction));
    }
}
