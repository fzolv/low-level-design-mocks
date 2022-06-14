package com.fzolv.lld.vendingmachine.model;

import lombok.Data;

@Data
public class Product {

    private Integer id;
    private String name;
    private String category;
    private Double cost;

}
