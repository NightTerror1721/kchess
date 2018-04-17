/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kp.games.kchess.board.PlayerId;
import kp.games.kchess.io.IOUtils;

/**
 *
 * @author Asus
 */
public enum EndgameState
{
    WIN_WHITE(PlayerId.WHITE),
    WIN_BLACK(PlayerId.BLACK),
    DRAW(null);
    
    private final PlayerId winnerPlayer;
    
    private EndgameState(PlayerId winnerPlayer)
    {
        this.winnerPlayer = winnerPlayer;
    }
    
    public final boolean isWinner(PlayerId player) { return winnerPlayer == player; }
    public final boolean isLooser(PlayerId player) { return winnerPlayer != player; }
    public final boolean isDrawState() { return this == DRAW; }
    
    public static final void serialize(OutputStream os, EndgameState es) throws IOException
    {
        IOUtils.writeSignedByte(os, (byte) es.ordinal());
    }
    
    public static final EndgameState unserialize(InputStream is) throws IOException
    {
        byte b = IOUtils.readSignedByte(is);
        switch(b)
        {
            case 0: return WIN_WHITE;
            case 1: return WIN_BLACK;
            case 2:
            default:
                return DRAW;
        }
    }
}
