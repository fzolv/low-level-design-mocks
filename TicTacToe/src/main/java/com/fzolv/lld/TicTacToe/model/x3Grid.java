package com.fzolv.lld.TicTacToe.model;

import java.util.Arrays;

public class x3Grid extends Grid {

    public x3Grid() {
        setSize(3);
        setPositions(new char[3][3]);
        initPositions();
    }

    private void initPositions() {
        for(int i=0;i<3;i++) {
            Arrays.fill(getPositions()[i], '.');
        }
    }
}
