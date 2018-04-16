/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import java.util.HashSet;
import java.util.Iterator;
import kp.games.kchess.board.Board.Rank;

/**
 *
 * @author mpasc
 */
public final class MoveSet implements Iterable<Rank>
{
    private final HashSet<Rank> ranks = new HashSet<>();
    private final Piece piece;
    private final Rank rank;
    private final Board board;
    
    public MoveSet(Rank rank)
    {
        if(!rank.hasPiece())
            throw new IllegalArgumentException();
        this.piece = rank.getPiece();
        this.rank = rank;
        this.board = rank.getBoard();
    }
    
    public final boolean isLegalMove(Rank rank)
    {
        return ranks.contains(rank);
    }
    
    public final boolean addNear(int drow, int dcolumn, Condition condition)
    {
        if(drow == 0 && dcolumn == 0)
            return false;
        int row = piece.isBlack() ? rank.getRow() - drow : rank.getRow() + drow;
        int column = dcolumn + rank.getColumn();
        if(row < Board.MIN_ROW || row > Board.MAX_ROW || column < Board.MIN_COLUMN || column > Board.MAX_COLUMN)
            return false;
        Rank trank = board.getRank(row, column);
        if(!trank.hasPiece())
        {
            if(condition != Condition.ONLY_CAPTURE)
            {
                ranks.add(trank);
                return true;
            }
            return false;
        }
        if(condition != Condition.DISABLE_CAPTURE && trank.getPiece().getPlayer() != piece.getPlayer())
            ranks.add(trank);
        return false;
    }
    public final boolean addNear(int drow, int dcolumn) { return addNear(drow, dcolumn, Condition.NORMAL); }
    
    public final int addNearIfPath(int drow, int dcolumn, int paths, int path)
    {
        if((paths & path) != path)
            return paths;
        if(!addNear(drow, dcolumn))
            paths &= ~path;
        return paths;
    }

    @Override
    public final Iterator<Rank> iterator() { return ranks.iterator(); }
    
    public enum Condition { NORMAL, DISABLE_CAPTURE, ONLY_CAPTURE; }
}
