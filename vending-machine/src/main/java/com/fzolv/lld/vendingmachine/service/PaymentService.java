package com.fzolv.lld.vendingmachine.service;

import com.fzolv.lld.vendingmachine.model.VendingMachine;
import com.fzolv.lld.vendingmachine.strategy.TenderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    TenderStrategy tenderStrategy;

    public void addAmount(VendingMachine vendingMachine, Double cash) {
        synchronized (vendingMachine.getId()) {
            tenderStrategy.credit(vendingMachine.getCashRegister(), cash);
        }
    }

    public void deductAmount(VendingMachine vendingMachine, Double cash) {
        synchronized (vendingMachine.getId()) {
            tenderStrategy.deduct(vendingMachine.getCashRegister(), cash);
        }
    }

}
