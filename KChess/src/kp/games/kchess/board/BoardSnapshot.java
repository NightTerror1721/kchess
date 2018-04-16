/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kp.games.kchess.board.Board.Rank;
import kp.games.kchess.io.IOUtils;

/**
 *
 * @author Asus
 */
public final class BoardSnapshot
{
    final byte[] data = new byte[Board.ROWS * Board.COLUMNS];
    
    private BoardSnapshot() {}
    
    public final void fillBoard(Board board) { board.fillFromSnapshot(this); }
    
    public static final BoardSnapshot createSnapshotFromBoard(Board board)
    {
        BoardSnapshot s = new BoardSnapshot();
        
        int i = -1;
        for(Rank rank : board)
        {
            i++;
            if(rank.hasPiece())
            {
                Piece p = rank.getPiece();
                s.data[i] = (byte) (0x1 | ((p.getPlayer().ordinal() & 0x1) << 1) | ((p.getPieceType().ordinal()) << 2));
            }
        }
        return s;
    }
    
    public static final void serialize(OutputStream output, BoardSnapshot snapshot) throws IOException
    {
        output.write(snapshot.data);
    }
    
    public static final BoardSnapshot unserialize(InputStream input) throws IOException
    {
        BoardSnapshot s = new BoardSnapshot();
        IOUtils.readFully(input, s.data);
        return s;
    }
}
