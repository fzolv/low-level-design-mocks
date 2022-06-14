package com.fzolv.lld.vendingmachine.exception;

public class ItemUnavailableException extends RuntimeException {

    public ItemUnavailableException(Integer item) {
        super(String.format("%s is unavailable. Sorry for inconvenience", item));
    }

}
