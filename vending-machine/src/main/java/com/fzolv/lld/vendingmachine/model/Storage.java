package com.fzolv.lld.vendingmachine.model;

import lombok.Data;

import java.util.Map;

@Data
public class Storage {

    private Map<Integer, Integer> productCountMap;
    private Map<Integer, Product> productMap;
}
