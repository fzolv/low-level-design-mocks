package com.fzolv.lld.TicTacToe.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Game {

    public Game() { }

    private Grid grid;

    private List<Player> players;

    private GameStatus status = GameStatus.NOT_STARTED;

    private int currentTurn = 0;
}
