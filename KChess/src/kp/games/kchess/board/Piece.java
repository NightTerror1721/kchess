/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import java.util.Objects;
import kp.games.kchess.board.Board.Rank;

/**
 *
 * @author Asus
 */
public final class Piece
{
    private PieceType type;
    private PlayerId player;
    
    private MoveSet moves;
    
    Rank rank;
    
    public Piece(PieceType type, PlayerId player)
    {
        this.type = Objects.requireNonNull(type);
        this.player = Objects.requireNonNull(player);
    }
    
    public final PieceType getPieceType() { return type; }
    public final PlayerId getPlayer() { return player; }
    
    public final void setPieceType(PieceType type) { this.type = Objects.requireNonNull(type); }
    public final void setPlatyer(PlayerId player) { this.player = Objects.requireNonNull(player); }
    
    public final boolean isWhite() { return player == PlayerId.WHITE; }
    public final boolean isBlack() { return player == PlayerId.BLACK; }
    
    public final boolean hasRank() { return rank != null; }
    public final Rank getRank()
    {
        if(rank == null)
            throw new NullPointerException();
        return rank;
    }
    public final Board getBoard() { return getRank().getBoard(); }
    
    public final MoveSet getMoveSet()
    {
        if(moves == null)
            return rank == null ? null : (moves = type.generateMoveSet(rank));
        return moves;
    }
    
    public final void clearMoves() { moves = null; }
    public final MoveSet generateMoves()
    {
        if(rank != null)
            return moves = type.generateMoveSet(rank);
        return moves = null;
    }
    
}
