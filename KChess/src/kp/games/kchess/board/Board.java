/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import java.util.Iterator;
import java.util.Objects;
import kp.games.kchess.board.Board.Rank;

/**
 *
 * @author Asus
 */
public final class Board implements Iterable<Rank>
{
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;
    
    public static final int MAX_ROW = ROWS - 1;
    public static final int MIN_ROW = 0;
    
    public static final int MAX_COLUMN = COLUMNS - 1;
    public static final int MIN_COLUMN = 0;
    
    public static final int MIN_ID = 0;
    public static final int MAX_ID = ROWS * COLUMNS - 1;
    
    private final Rank[] ranks;
    
    public Board()
    {
        ranks = new Rank[ROWS * COLUMNS];
        for(int i=0;i<ranks.length;i++)
            ranks[i] = new Rank(i);
    }
    
    public final Rank getRank(int id)
    {
        if(id < 0 || id >= ranks.length)
            throw new IllegalArgumentException("Invalid id [0-" + (ranks.length - 1) + "]: " + id);
        return ranks[id];
    }
    public final Rank getRank(int row, int column)
    {
        if(row < 0 || row >= ROWS)
            throw new IllegalArgumentException("Invalid row [0-7]: " + row);
        if(column < 0 || column >= COLUMNS)
            throw new IllegalArgumentException("Invalid column [0-7]: " + column);
        return ranks[row * COLUMNS + column];
    }
    public final Rank getRank(String name)
    {
        if(name.length() != 2)
            throw new IllegalArgumentException("Invalid rank name [A-H][1-8]: " + name);
        int row = getRowNumber(name.charAt(0));
        int column = getColumnNumber(name.charAt(1));
        return ranks[row * COLUMNS + column];
    }
    
    public final BoardSnapshot createSnapshot() { return BoardSnapshot.createSnapshotFromBoard(this); }
    
    public final void fillFromSnapshot(BoardSnapshot snapshot)
    {
        for(int i=0;i<ranks.length;i++)
        {
            byte b = snapshot.data[i];
            if((b & 0x1) != 0x1)
            {
                if(ranks[i].hasPiece())
                    ranks[i].removePiece();
            }
            else
            {
                PlayerId player = ((b >>> 1) & 0x1) == 0x1 ? PlayerId.BLACK : PlayerId.WHITE;
                PieceType type = PieceType.valueOf((b >>> 2) & 0x7);
                Piece p = new Piece(type, player);
                if(ranks[i].hasPiece())
                    ranks[i].replacePiece(p);
                else ranks[i].setPiece(p);
            }
        }
    }
    
    @Override
    public final Iterator<Rank> iterator()
    {
        return new Iterator<Rank>()
        {
            private int it = 0;
            
            @Override
            public final boolean hasNext() { return it < ranks.length; }

            @Override
            public final Rank next() { return ranks[it++]; }
            
        };
    }
    
    
    
    
    
    public static final char getRowName(int row)
    {
        if(row < 0 || row >= ROWS)
            throw new IllegalArgumentException();
        return (char) ('1' + row);
    }
    public static final int getRowNumber(char row)
    {
        int num = row - '1';
        if(num < 0 || num >= ROWS)
            throw new IllegalArgumentException();
        return num;
    }
    
    public static final char getColumnName(int column)
    {
        if(column < 0 || column >= COLUMNS)
            throw new IllegalArgumentException();
        return (char) ('A' + column);
    }
    public static final int getColumnNumber(char column)
    {
        int num = column - 'A';
        if(num < 0 || num >= COLUMNS)
            throw new IllegalArgumentException();
        return num;
    }
    
    public static final String idToName(int id)
    {
        if(id < MIN_ID || id > MAX_ID)
            throw new IllegalArgumentException();
        return new StringBuilder().append(getColumnName(id % COLUMNS)).append(getRowName(id/ COLUMNS)).toString();
    }
    
    
    
    public final class Rank
    {
        private final int id;
        private final int row;
        private final int column;
        private final String name;
        private Piece piece;
        
        private Rank(int id)
        {
            this.id = id;
            this.row = id / COLUMNS;
            this.column = id % COLUMNS;
            this.name = String.valueOf(getColumnName(column)) + getRowName(row);
        }
        
        public final int getId() { return id; }
        public final int getRow() { return row; }
        public final int getColumn() { return column; }
        public final String getName() { return name; }
        public final Board getBoard() { return Board.this; }
        
        public final boolean hasPiece() { return piece != null; }
        
        public final Piece getPiece()
        {
            if(piece == null)
                throw new NullPointerException();
            return piece;
        }
        
        private void setPiece(Piece piece)
        {
            if(this.piece != null)
                throw new IllegalStateException();
            this.piece = Objects.requireNonNull(piece);
            this.piece.rank = this;
        }
        
        private Piece removePiece()
        {
            if(piece == null)
                throw new IllegalStateException();
            Piece p = piece;
            piece = null;
            p.rank = null;
            return p;
        }
        
        private void replacePiece(Piece newPiece)
        {
            if(piece == null)
                throw new IllegalStateException();
            if(newPiece.rank != null)
                throw new IllegalStateException();
            removePiece();
            setPiece(newPiece);
        }
    }
}
