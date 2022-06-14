package com.fzolv.lld.TicTacToe.service;

import com.fzolv.lld.TicTacToe.model.Player;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {

    Map<String, Player> players;

    int p;

    @PostConstruct
    public void init() {
        players = new HashMap<>();
        p = 0;
    }

    public String createPlayer(Player pl) {
        String playerId = String.valueOf(p);
        pl.setId(playerId);
        players.put(playerId, pl);
        p++;
        return playerId;
    }

    public Player getPlayer(String playerId) {
        return players.get(playerId);
    }

    public void deletePlayer(String playerId) {
        players.remove(playerId);
    }
}
