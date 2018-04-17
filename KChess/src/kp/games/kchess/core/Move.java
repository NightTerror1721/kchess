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
import kp.games.kchess.board.Piece;
import kp.games.kchess.board.PlayerId;
import kp.games.kchess.io.IOUtils;


/**
 *
 * @author Asus
 */
public final class Move
{
    private final PlayerId player;
    private final int from;
    private final int to;

    private Move(PlayerId player, Rank from, Rank to)
    {
        this.player = player;
        this.from = from.getId();
        this.to = to.getId();
    }
    
    private Move(PlayerId player, int from, int to)
    {
        this.player = player;
        this.from = from;
        this.to = to;
    }
    
    public static final Move create(PlayerId player, int from, int to, Board board)
    {
        Rank rfrom = board.getRank(from);
        Rank rto = board.getRank(to);
        if(!rfrom.hasPiece())
            throw new IllegalArgumentException();
        Piece p = rfrom.getPiece();
        if(p.getPlayer() != player)
            throw new IllegalArgumentException();
        if(!p.getMoveSet().isLegalMove(rto))
            throw new IllegalArgumentException();
        return new Move(player, rfrom, rto);
    }

    public final PlayerId getPlayer() { return player; }
    public final int getFromId() { return from; }
    public final int getToId() { return to; }

    @Override
    public final String toString() { return Board.idToName(from) + " -> " + Board.idToName(to); }
    
    public static final void serialize(OutputStream os, Move move) throws IOException
    {
        PlayerId.serialize(os, move.player);
        IOUtils.writeSignedInt32(os, move.from);
        IOUtils.writeSignedInt32(os, move.to);
    }
    
    public static final Move unserialize(InputStream is) throws IOException
    {
        PlayerId player = PlayerId.unserialize(is);
        int from = IOUtils.readSignedInt32(is);
        int to = IOUtils.readSignedInt32(is);
        return new Move(player, from, to);
    }
}
