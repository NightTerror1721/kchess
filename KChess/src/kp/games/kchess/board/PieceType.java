/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.board;

import kp.games.kchess.board.Board.Rank;
import kp.games.kchess.board.MoveSet.Condition;

/**
 *
 * @author Asus
 */
public enum PieceType
{
    KING,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN;
    
    private static final int MIN_POSITION = 1;
    private static final int MAX_POSITION = 8;
    
    private static final int RIGHT_PATH = 0x1;
    private static final int UP_RIGHT_PATH = 0x10;
    private static final int UP_PATH = 0x2;
    private static final int UP_LEFT_PATH = 0x20;
    private static final int LEFT_PATH = 0x4;
    private static final int DOWN_LEFT_PATH = 0x40;
    private static final int DOWN_PATH = 0x8;
    private static final int DOWN_RIGHT_PATH = 0x80;
    
    private static final int NORMAL_PATHS = RIGHT_PATH + UP_PATH + LEFT_PATH + DOWN_PATH;
    private static final int DIAGONAL_PATHS = UP_RIGHT_PATH + UP_LEFT_PATH + DOWN_LEFT_PATH + DOWN_RIGHT_PATH;
    private static final int ALL_PATHS = NORMAL_PATHS + DIAGONAL_PATHS;
    
    public final MoveSet generateMoveSet(Rank rank)
    {
        MoveSet moves = new MoveSet(rank);
        switch(this)
        {
            default: throw new IllegalStateException();
            case KING: {
                moves.addNear(0, 1);
                moves.addNear(1, 1);
                moves.addNear(1, 0);
                moves.addNear(1, -1);
                moves.addNear(0, -1);
                moves.addNear(-1, -1);
                moves.addNear(-1, 0);
                moves.addNear(-1, 1);
            } break;
            case QUEEN: {
                int paths = ALL_PATHS;
                for(int i = MIN_POSITION; paths != 0 && i <= MAX_POSITION; i++)
                {
                    moves.addNearIfPath(0, i, paths, RIGHT_PATH);
                    moves.addNearIfPath(i, 0, paths, UP_PATH);
                    moves.addNearIfPath(0, -i, paths, LEFT_PATH);
                    moves.addNearIfPath(-i, 0, paths, DOWN_PATH);

                    moves.addNearIfPath(i, i, paths, UP_RIGHT_PATH);
                    moves.addNearIfPath(i, i, paths, UP_LEFT_PATH);
                    moves.addNearIfPath(i, i, paths, DOWN_LEFT_PATH);
                    moves.addNearIfPath(i, i, paths, DOWN_RIGHT_PATH);
                }
            } break;
            case ROOK: {
                int paths = NORMAL_PATHS;
                for(int i = MIN_POSITION; paths != 0 && i <= MAX_POSITION; i++)
                {
                    moves.addNearIfPath(0, i, paths, RIGHT_PATH);
                    moves.addNearIfPath(i, 0, paths, UP_PATH);
                    moves.addNearIfPath(0, -i, paths, LEFT_PATH);
                    moves.addNearIfPath(-i, 0, paths, DOWN_PATH);
                }
            } break;
            case BISHOP: {
                int paths = DIAGONAL_PATHS;
                for(int i = MIN_POSITION; paths != 0 && i <= MAX_POSITION; i++)
                {
                    moves.addNearIfPath(i, i, paths, UP_RIGHT_PATH);
                    moves.addNearIfPath(i, i, paths, UP_LEFT_PATH);
                    moves.addNearIfPath(i, i, paths, DOWN_LEFT_PATH);
                    moves.addNearIfPath(i, i, paths, DOWN_RIGHT_PATH);
                }
            } break;
            case KNIGHT: {
                moves.addNear(2, 1);
                moves.addNear(2, -1);

                moves.addNear(1, 2);
                moves.addNear(1, -2);

                moves.addNear(-1, 2);
                moves.addNear(-1, -2);

                moves.addNear(-2, 1);
                moves.addNear(-2, -1);
            } break;
            case PAWN: {
                if(moves.addNear(1, 0, Condition.DISABLE_CAPTURE))
                    moves.addNear(2, 0, Condition.DISABLE_CAPTURE);
                moves.addNear(1, 1, Condition.ONLY_CAPTURE);
                moves.addNear(1, -1, Condition.ONLY_CAPTURE);
            } break;
        }
        return moves;
    }
    
    private static final PieceType[] VALUES = values();
    public static final PieceType valueOf(int value)
    {
        if(value < 0 || value >= VALUES.length)
            throw new IllegalArgumentException();
        return VALUES[value];
    }
}
