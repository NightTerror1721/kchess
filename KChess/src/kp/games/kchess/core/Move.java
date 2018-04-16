/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kp.games.kchess.board.Board;
import kp.games.kchess.board.Board.Rank;
import kp.games.kchess.board.BoardSnapshot;
import kp.games.kchess.io.IOUtils;


/**
 *
 * @author Asus
 */
public final class Move
{
    private final int from;
    private final int to;
    private final BoardSnapshot state;

    private Move(Rank from, Rank to, BoardSnapshot state)
    {
        this.from = from.getId();
        this.to = to.getId();
        this.state = state;
    }
    
    private Move(int from, int to, BoardSnapshot state)
    {
        this.from = from;
        this.to = to;
        this.state = state;
    }

    public final int getFromId() { return from; }
    public final int getToId() { return to; }

    public final BoardSnapshot getState() { return state; }

    @Override
    public final String toString() { return Board.idToName(from) + " -> " + Board.idToName(to); }
    
    public static final void serialize(OutputStream os, Move move) throws IOException
    {
        IOUtils.writeSignedInt32(os, move.from);
        IOUtils.writeSignedInt32(os, move.to);
        BoardSnapshot.serialize(os, move.state);
    }
    
    public static final Move unserialize(InputStream is) throws IOException
    {
        int from = IOUtils.readSignedInt32(is);
        int to = IOUtils.readSignedInt32(is);
        BoardSnapshot state = BoardSnapshot.unserialize(is);
        return new Move(from, to, state);
    }
}
