/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

/**
 *
 * @author Asus
 */
public enum CommandType
{
    INVALID,
    CHAT_MESSAGE,
    MOVE,
    MOVE_RESULT,
    PLAYER_TURN,
    ENDGAME;
    
    private static final CommandType[] VALUES = values();
    public static final CommandType valueOf(int ordinal)
    {
        if(ordinal < 0 || ordinal >= VALUES.length)
            return null;
        return VALUES[ordinal];
    }
}
