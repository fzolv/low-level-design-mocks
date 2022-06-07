package com.fzolv.lld.TicTacToe.engine;

import com.fzolv.lld.TicTacToe.model.Game;
import com.fzolv.lld.TicTacToe.model.GameStatus;
import com.fzolv.lld.TicTacToe.model.Grid;
import com.fzolv.lld.TicTacToe.model.Position;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.stream.IntStream;

@Service
public class TicTacToeEngine {

    public GameStatus executeTurn(Game game, char marker, Position position) {

        Grid grid = game.getGrid();
        int n = grid.getSize();
        if(!grid.markPosition(position, marker)) {
            throw new InvalidParameterException("Already marked position.");
        }

        if(IntStream.range(0, n).allMatch(i -> grid.getPositions()[i][position.getY()] == marker)) {
            return GameStatus.WON;
        }
        if(IntStream.range(0, n).allMatch(i -> grid.getPositions()[position.getX()][i] == marker)) {
            return GameStatus.WON;
        }

        if(position.getX() == position.getY() || position.getX() == n - position.getY() -1) {
            if(IntStream.range(0, n).allMatch(i -> grid.getPositions()[i][i] == marker))
                return GameStatus.WON;
            if(IntStream.range(0, n).allMatch(i -> grid.getPositions()[i][n-1-i] == marker))
                return GameStatus.WON;
        }

        if(grid.isFull())
            return GameStatus.DRAW;

        return GameStatus.ONGOING;
    }

}
