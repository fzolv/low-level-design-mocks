package com.fzolv.lld.vendingmachine.service;

import com.fzolv.lld.vendingmachine.model.Transaction;
import com.fzolv.lld.vendingmachine.model.VendingMachine;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TransactionService {

    @Autowired
    MachineService machineService;
    Map<Integer, Lock> transactionLedger;
    Map<Integer, VendingMachine> stateStore;

    @PostConstruct
    public void init() {
        transactionLedger = new HashMap<>();
        stateStore = new HashMap<>();
    }

    public boolean beginTransaction(Transaction transaction) {
        Integer machineId = transaction.getMachineId();

        if(transactionLedger.containsKey(machineId)){
            boolean state = transactionLedger.get(machineId).tryLock();
            if(!state) return false;
            stateStore.put(machineId, machineService.get(machineId).clone());
            return true;
        }
        Lock lock = new ReentrantLock();
        transactionLedger.put(machineId, lock);
        stateStore.put(machineId, machineService.get(machineId).clone());
        return lock.tryLock();
    }

    public void commitTransaction(Integer machineId) {
        stateStore.remove(machineId);
        transactionLedger.get(machineId).unlock();
    }

    public void rollbackTransaction(Integer machineId) {
        machineService.reset(machineId, stateStore.get(machineId));
        stateStore.remove(machineId);
        transactionLedger.get(machineId).unlock();
    }

}
