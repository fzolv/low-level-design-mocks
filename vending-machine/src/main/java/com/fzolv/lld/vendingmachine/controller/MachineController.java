package com.fzolv.lld.vendingmachine.controller;

import com.fzolv.lld.vendingmachine.model.VendingMachine;
import com.fzolv.lld.vendingmachine.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MachineController {

    @Autowired
    MachineService vendingService;

    @PostMapping("/machine")
    public ResponseEntity<Integer> startVendingMachine(@RequestBody VendingMachine vendingMachine) {
        return ResponseEntity.ok(vendingService.start(vendingMachine));
    }

    @GetMapping("/machine/{machineId}")
    public ResponseEntity<VendingMachine> getVendingMachine(@PathVariable("machineId") Integer machineId) {
        return ResponseEntity.ok(vendingService.get(machineId));
    }

    @DeleteMapping("/machine/{machineId}")
    public ResponseEntity<Void> stopVendingMachine(@PathVariable("machineId") Integer machineId) {
        vendingService.stop(machineId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/machine/{machineId}")
    public ResponseEntity<Void> updateMachingStatus(@PathVariable("machineId") Integer machineId, @RequestParam("status") Boolean isWorking) {
        vendingService.updateStatus(machineId, isWorking);
        return ResponseEntity.ok().build();
    }

}
