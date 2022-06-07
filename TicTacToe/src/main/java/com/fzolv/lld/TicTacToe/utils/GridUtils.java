package com.fzolv.lld.TicTacToe.utils;

import com.fzolv.lld.TicTacToe.model.Grid;
import com.fzolv.lld.TicTacToe.model.Position;

public class GridUtils {

    private GridUtils() {}

    public static boolean isValid(Grid grid, Position position) {
        int size = grid.getSize();
        return position.isValid() && position.getX()< size && position.getY()<size;

    }
}
