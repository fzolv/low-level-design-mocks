package com.fzolv.lld.vendingmachine.model;

import lombok.Data;

@Data
public class VendingMachine implements Cloneable {

    private Integer id;
    private Storage storage;
    private CashRegister cashRegister;
    private Boolean working;

    public VendingMachine clone() {
        try {
            return (VendingMachine) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
