package com.fzolv.lld.TicTacToe.service;

import com.fzolv.lld.TicTacToe.engine.TicTacToeEngine;
import com.fzolv.lld.TicTacToe.factory.GridFactory;
import com.fzolv.lld.TicTacToe.model.Game;
import com.fzolv.lld.TicTacToe.model.Grid;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    Map<String, Game> games;

    int count;

    @PostConstruct
    public void init() {
        count = 0;
        games = new HashMap<>();
    }

    public Game get(String gameId) {
        return games.get(gameId);
    }

    public String create(Game game) {
        String gameId = String.valueOf(count);
        Grid x3 = GridFactory.createGrid(3);
        game.setGrid(x3);
        games.put(gameId, game);
        game.setCurrentTurn(0);
        count++;
        return gameId;
    }

    public void delete(String gameId) {
        games.remove(gameId);
    }
}
