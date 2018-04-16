/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

import java.util.Objects;
import kp.games.kchess.board.Board;
import kp.games.kchess.board.PlayerId;

/**
 *
 * @author Asus
 */
public final class GameCore
{
    private final Pipeline[] pipelines;
    private final Board board;
    private PlayerId playerTurn;
    
    public GameCore(Board board, Pipeline white, Pipeline black)
    {
        this.board = Objects.requireNonNull(board);
        this.pipelines = new Pipeline[] {
            Objects.requireNonNull(white),
            Objects.requireNonNull(black)
        };
    }
}
