package com.fzolv.lld.vendingmachine.strategy;

import com.fzolv.lld.vendingmachine.exception.InsufficientChangeException;
import com.fzolv.lld.vendingmachine.model.CashRegister;
import org.springframework.stereotype.Component;

@Component
public class TenderStrategy {

    public void credit(CashRegister register, Double amount) {
        register.setCash(register.getCash() + amount);
    }

    public void deduct(CashRegister register, Double amount) throws InsufficientChangeException {
        if(register.getCash() < amount)
            throw new InsufficientChangeException();
        register.setCash(register.getCash() - amount);
    }
}
