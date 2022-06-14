package com.fzolv.lld.vendingmachine.service;

import com.fzolv.lld.vendingmachine.aspect.TransactionEnabled;
import com.fzolv.lld.vendingmachine.exception.InsufficientMoneyException;
import com.fzolv.lld.vendingmachine.exception.ItemUnavailableException;
import com.fzolv.lld.vendingmachine.exception.MachineOFOException;
import com.fzolv.lld.vendingmachine.model.Product;
import com.fzolv.lld.vendingmachine.model.Storage;
import com.fzolv.lld.vendingmachine.model.Transaction;
import com.fzolv.lld.vendingmachine.model.VendingMachine;
import com.fzolv.lld.vendingmachine.stream.ThrowingConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

import static java.lang.Boolean.TRUE;

@Service
public class VendingService {

    @Autowired
    MachineService machineService;

    @Autowired
    StorageService storageService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    BillingService billingService;

    @TransactionEnabled
    public Double vend(Transaction transaction) {

        VendingMachine vendingMachine = machineService.get(transaction.getMachineId());

        if(!vendingMachine.getWorking().equals(TRUE))
            throw new MachineOFOException();
        Map<Integer, Product> productMap = vendingMachine.getStorage().getProductMap();
        Map<Integer, Integer> productCount = vendingMachine.getStorage().getProductCountMap();
        transaction.getProducts().entrySet().stream().forEach( k -> {
            if(!productMap.containsKey(k.getKey()))
                throw new ItemUnavailableException(k.getKey());
            if(productCount.get(k.getKey()) < k.getValue()) {
                throw new ItemUnavailableException(k.getKey());
            }
        });

        synchronized(transaction.getMachineId()) {
            Double billing = billingService.calculateBill(vendingMachine, transaction.getProducts());
            if(billing > transaction.getCashInserted())
                throw new InsufficientMoneyException();
            Double balance = transaction.getCashInserted() - billing;
            storageService.removeProducts(vendingMachine, transaction.getProducts());
            paymentService.addAmount(vendingMachine, billing);
            return balance;
        }
    }
}
