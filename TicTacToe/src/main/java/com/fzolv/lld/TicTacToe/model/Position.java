package com.fzolv.lld.TicTacToe.model;

import lombok.Data;

@Data
public class Position {

    private Integer x;
    private Integer y;

    public boolean isValid() {
        return x!=null && y != null && x>=0 && y>=0;
    }
}
