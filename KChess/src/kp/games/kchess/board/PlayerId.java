/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kp.games.kchess.io.IOUtils;

/**
 *
 * @author Asus
 */
public enum PlayerId
{
    WHITE,
    BLACK;
    
    private final String scode;
    
    private PlayerId()
    {
        this.scode = "<" + name() + ">";
    }
    
    public final String getStringCode() { return scode; }
    
    public final PlayerId inverse() { return this == WHITE ? BLACK : WHITE; }
    
    public static final void serialize(OutputStream os, PlayerId player) throws IOException
    {
        IOUtils.writeSignedByte(os, (byte) player.ordinal());
    }
    
    public static final PlayerId unserialize(InputStream is) throws IOException
    {
        return IOUtils.readSignedByte(is) == 0 ? WHITE : BLACK;
    }
    
    public static final PlayerId codeToPlayer(String code)
    {
        if(WHITE.scode.equals(code))
            return WHITE;
        return BLACK.scode.equals(code) ? BLACK : null;
    }
}
