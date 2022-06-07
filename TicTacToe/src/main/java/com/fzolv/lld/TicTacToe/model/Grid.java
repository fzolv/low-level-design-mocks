package com.fzolv.lld.TicTacToe.model;

import lombok.Data;

@Data
public abstract class Grid {

    private String id;

    private int size;

    private char[][] positions;

    private int positionsFilled;

    public boolean markPosition(Position p, char marker) {
        if(positions[p.getX()][p.getY()] != '.')
            return false;
        positions[p.getX()][p.getY()] = marker;
        positionsFilled++;
        return true;
    }

    public boolean isFull() {
        return positionsFilled == size*size;
    }

}
