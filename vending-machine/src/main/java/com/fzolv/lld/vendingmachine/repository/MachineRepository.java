package com.fzolv.lld.vendingmachine.repository;

import com.fzolv.lld.vendingmachine.model.VendingMachine;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MachineRepository implements MapRepository<Integer, VendingMachine> {

    Map<Integer, VendingMachine> vendingStore;

    @PostConstruct
    public void init() {
        vendingStore = new HashMap<>();
    }

    @Override
    public VendingMachine get(Integer machineId) {
        return vendingStore.get(machineId);
    }

    @Override
    public void update(Integer machineId, VendingMachine machine) {
        vendingStore.put(machineId, machine);
    }

    @Override
    public Integer add(VendingMachine vendingMachine) {
        Integer machineId = vendingStore.size();
        vendingMachine.setId(machineId);
        vendingStore.put(machineId, vendingMachine);
        return machineId;
    }

    @Override
    public void delete(Integer machineId) {
        vendingStore.remove(machineId);
    }
}
