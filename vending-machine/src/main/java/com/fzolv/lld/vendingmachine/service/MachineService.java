package com.fzolv.lld.vendingmachine.service;

import com.fzolv.lld.vendingmachine.model.VendingMachine;
import com.fzolv.lld.vendingmachine.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MachineService {

    @Autowired
    MachineRepository vendingRepository;

    public VendingMachine get(Integer machineId) {
        return vendingRepository.get(machineId);
    }

    public void reset(Integer machineId, VendingMachine vendingMachine) {
        vendingRepository.update(machineId, vendingMachine);
    }

    public Integer start(VendingMachine vendingMachine) {
        return vendingRepository.add(vendingMachine);
    }

    public void stop(Integer machineId) {
        vendingRepository.delete(machineId);
    }

    public void updateStatus(Integer machineId, Boolean status) { vendingRepository.get(machineId).setWorking(status); }

}
