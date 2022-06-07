package com.fzolv.lld.TicTacToe.controller;

import com.fzolv.lld.TicTacToe.engine.TicTacToeEngine;
import com.fzolv.lld.TicTacToe.model.Game;
import com.fzolv.lld.TicTacToe.model.GameStatus;
import com.fzolv.lld.TicTacToe.model.Player;
import com.fzolv.lld.TicTacToe.model.Turn;
import com.fzolv.lld.TicTacToe.service.GameService;
import com.fzolv.lld.TicTacToe.service.PlayerService;
import com.fzolv.lld.TicTacToe.utils.GridUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    TicTacToeEngine ticTacToeEngine;

    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable("gameId") String gameId) {
        return ResponseEntity.ok(gameService.get(gameId));
    }

    @PostMapping("/game")
    public ResponseEntity<String> startGame(@RequestBody Game game) {
        if(game.getPlayers() == null || game.getPlayers().size() < 2)
            return ResponseEntity.badRequest().body("No of players too low");
        for(Player p : game.getPlayers()) {
            if(p.getMarker() == null)
                return ResponseEntity.badRequest().body("Player marker not set");
            playerService.createPlayer(p);
        }
        return ResponseEntity.ok().body(gameService.create(game));
    }

    @DeleteMapping("/game/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") String gameId) {
        gameService.delete(gameId);
        return ResponseEntity.ok("Deleted the game");
    }

    @PostMapping("/game/turn")
    public ResponseEntity<String> processTurn(@RequestBody Turn turn) {
        Game game = gameService.get(turn.getGameId());
        if(game.getStatus().equals(GameStatus.WON) || game.getStatus().equals(GameStatus.DRAW)) {
            return ResponseEntity.badRequest().body("Game is already over");
        }
        if(!GridUtils.isValid(game.getGrid(), turn.getPosition())) {
            return ResponseEntity.badRequest().body("Illegal Position value passed");
        }
        Player currentPlayer = game.getPlayers().get(game.getCurrentTurn());
        if(!turn.getPlayerId().equals(currentPlayer.getId())){
            return ResponseEntity.badRequest().body("Player sent is not current player");
        }
        GameStatus status;
        try {
            status = ticTacToeEngine.executeTurn(game, currentPlayer.getMarker(), turn.getPosition());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Already marked position");
        }
        game.setStatus(status);
        int n = game.getPlayers().size();
        game.setCurrentTurn((game.getCurrentTurn()+1) % n);
        return ResponseEntity.ok(status.name());
    }
}
