package com.fzolv.lld.vendingmachine.service;

import com.fzolv.lld.vendingmachine.model.Storage;
import com.fzolv.lld.vendingmachine.model.VendingMachine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StorageService {

    @Autowired
    MachineService machineService;

    public void addProducts(VendingMachine vendingMachine, Map<Integer, Integer> products) {
        Storage storage = vendingMachine.getStorage();
        products.entrySet().forEach(i -> storage.getProductCountMap().merge(i.getKey(), i.getValue(), (p, c) -> p+c));
    }

    public void removeProducts(VendingMachine vendingMachine, Map<Integer, Integer> products) {
        Storage storage = vendingMachine.getStorage();
        products.entrySet().forEach(i -> storage.getProductCountMap().merge(i.getKey(), i.getValue(), (p, c) -> p-c));
    }

}
