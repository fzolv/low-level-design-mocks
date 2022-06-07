package com.fzolv.lld.TicTacToe.factory;

import com.fzolv.lld.TicTacToe.model.Game;

public class GameFactory {

    private GameFactory() { }

    public static Game startInstance() {
        return new Game();
    }

}
