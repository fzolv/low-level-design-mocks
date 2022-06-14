package com.fzolv.lld.vendingmachine.model;

import lombok.Data;

@Data
public class CashRegister {

    private String currency;
//  For now, treating money as double
    private Double cash;
//  TODO: Implement cash handling in coins and notes
//    private Integer notes;
//    private Integer coins;


}
