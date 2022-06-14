package com.fzolv.lld.vendingmachine.service;

import com.fzolv.lld.vendingmachine.model.Product;
import com.fzolv.lld.vendingmachine.model.VendingMachine;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BillingService {

    public Double calculateBill(VendingMachine vendingMachine, Map<Integer, Integer> products) {
        Map<Integer, Product> productMap = vendingMachine.getStorage().getProductMap();
        return products.entrySet().stream().map(p -> productMap.get(p.getKey()).getCost()*p.getValue()).reduce((a,b) -> a+b).orElseThrow();
    }
}
