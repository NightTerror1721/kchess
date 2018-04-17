/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import java.util.LinkedList;
import java.util.Objects;
import kp.games.kchess.board.Board.Rank;

/**
 *
 * @author Asus
 */
public final class PiecePool
{
    private static final int KING_ID = 0;
    private static final int QUEEN_ID = 1;
    private static final int ROOK_FIRST_ID = 2;
    private static final int BISHOP_FIRST_ID = 4;
    private static final int KNIGHT_FIRST_ID = 6;
    private static final int PAWN_FIRST_ID = 8;
    
    private final Board board;
    private final PlayerId player;
    private final Piece[] all;
    private final Piece king;
    
    private final LinkedList<Piece> alive;
    
    public PiecePool(Board board, PlayerId player)
    {
        this.board = Objects.requireNonNull(board);
        this.player = Objects.requireNonNull(player);
        
        all = new Piece[16];
        
        all[0] = king = new Piece(PieceType.KING, player);
        all[1] = new Piece(PieceType.QUEEN, player);
        all[2] = new Piece(PieceType.ROOK, player);
        all[3] = new Piece(PieceType.ROOK, player);
        all[4] = new Piece(PieceType.BISHOP, player);
        all[5] = new Piece(PieceType.BISHOP, player);
        all[6] = new Piece(PieceType.KNIGHT, player);
        all[7] = new Piece(PieceType.KNIGHT, player);
        for(int i=8;i<all.length;i++)
            all[i] = new Piece(PieceType.PAWN, player);
        
        alive = new LinkedList<>();
    }
    
    public final PlayerId getPlayer() { return player; }
    
    public final Piece getKing() { return king; }
    
    public final void resurrectAllInDefaultLocations()
    {
        alive.clear();
        
        resurrectInLocation(all[KING_ID], 0, 4);
        resurrectInLocation(all[QUEEN_ID], 0, 3);
        
        resurrectInLocation(all[ROOK_FIRST_ID], 0, 0);
        resurrectInLocation(all[ROOK_FIRST_ID + 1], 0, 7);
        
        resurrectInLocation(all[BISHOP_FIRST_ID], 0, 2);
        resurrectInLocation(all[BISHOP_FIRST_ID + 1], 0, 5);
        
        resurrectInLocation(all[KNIGHT_FIRST_ID], 0, 1);
        resurrectInLocation(all[KNIGHT_FIRST_ID + 1], 0, 6);
        
        for(int i=0;i<8;i++)
            resurrectInLocation(all[PAWN_FIRST_ID + i], 1, i);
    }
    private void resurrectInLocation(Piece p, int row, int column)
    {
        if(player == PlayerId.BLACK)
            row = Board.MAX_ROW - row;
        if(p.hasRank())
            p.getRank().removePiece();
        Rank rank = board.getRank(row, column);
        if(rank.hasPiece())
            rank.replacePiece(p);
        else rank.setPiece(p);
    }
    
    public final boolean hasCheckInLocation(PiecePool others, int row, int column)
    {
        if(others.board != board)
            throw new IllegalArgumentException();
        if(others.player.inverse() != player)
            throw new IllegalArgumentException();
        
        Rank rank = board.getRank(row, column);
        for(Piece p : others.alive)
            if(p.getMoveSet().isLegalMove(rank))
                return true;
        return false;
    }
    
    public final boolean hasCheck(PiecePool others)
    {
        if(!king.hasRank())
            return false;
        Rank rank = king.getRank();
        return hasCheckInLocation(others, rank.getRow(), rank.getColumn());
    }
    
    public final boolean hasCheckmate(PiecePool others)
    {
        if(!king.hasRank())
            return false;
        for(Rank rank : king.getMoveSet())
            if(!hasCheckInLocation(others, rank.getRow(), rank.getColumn()))
                return false;
        return true;
    }
}
