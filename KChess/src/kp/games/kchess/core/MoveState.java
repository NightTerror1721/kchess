/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import kp.games.kchess.board.BoardSnapshot;

/**
 *
 * @author Asus
 */
public final class MoveState
{
    private final Move move;
    private final BoardSnapshot initialState;
    
    public MoveState(Move move, BoardSnapshot initialState)
    {
        this.move = Objects.requireNonNull(move);
        this.initialState = Objects.requireNonNull(initialState);
    }
    
    public final Move getMove() { return move; }
    public final BoardSnapshot getInitialState() { return initialState; }
    
    @Override
    public final String toString() { return move.toString(); }
    
    public static final void serialize(OutputStream os, MoveState ms) throws IOException
    {
        Move.serialize(os, ms.move);
        BoardSnapshot.serialize(os, ms.initialState);
    }
    
    public static final MoveState unserialize(InputStream is) throws IOException
    {
        Move move = Move.unserialize(is);
        BoardSnapshot initialState = BoardSnapshot.unserialize(is);
        return new MoveState(move, initialState);
    }
}
