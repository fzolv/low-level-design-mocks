package com.fzolv.lld.TicTacToe.factory;

import com.fzolv.lld.TicTacToe.model.Grid;
import com.fzolv.lld.TicTacToe.model.x3Grid;

public class GridFactory {

    public static Grid createGrid(int size) {
        return switch(size) {
            case 3 -> new x3Grid();
            default -> throw new IllegalStateException();
        };
    }
}
