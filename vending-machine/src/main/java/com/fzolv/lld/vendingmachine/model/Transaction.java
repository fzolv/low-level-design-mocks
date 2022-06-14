package com.fzolv.lld.vendingmachine.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class Transaction {
    @NotNull(message = "Machine id cannot be null")
    Integer machineId;
    @NotNull(message = "Product list cannot be null")
    Map<Integer, Integer> products;
    @NotNull(message = "Cash inserted cannot be null")
    Double cashInserted;
}
