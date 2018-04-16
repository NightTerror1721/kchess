/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

import java.util.Objects;
import kp.games.kchess.board.PlayerId;

/**
 *
 * @author Asus
 */
public final class Command
{
    public static final Command INVALID = new Command(CommandType.INVALID);
    
    private final CommandType type;
    private final Object[] data;
    
    private Command(CommandType type, Object... data)
    {
        this.type = Objects.requireNonNull(type);
        this.data = Objects.requireNonNull(data);
    }
    
    public final CommandType getCommandType() { return type; }
    
    public final int getDataCount() { return data.length; }
    public final <T> T getData(int index) { return (T) data[index]; }
    
    public final boolean isInvalid() { return type == CommandType.INVALID; }
    
    
    
    public static final Command chatMessage(String username, String text)
    {
        return new Command(CommandType.CHAT_MESSAGE, username, text);
    }
    
    public static final Command move(PlayerId player, Move move)
    {
        return new Command(CommandType.MOVE, player, move);
    }
}
