package com.fzolv.lld.TicTacToe.model;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class Turn {

    @NonNull
    String gameId;

    @NonNull
    String playerId;

    Position position;
}
