/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kp.games.kchess.board.PlayerId;
import kp.games.kchess.core.Command;
import kp.games.kchess.core.CommandType;
import kp.games.kchess.core.Move;

/**
 *
 * @author Asus
 */
public final class CommandIO
{
    private CommandIO() {}
    
    public static final void serialize(OutputStream os, Command command) throws IOException
    {
        CommandType type = command.getCommandType();
        IOUtils.writeSignedInt32(os, type.ordinal());
        switch(type)
        {
            case CHAT_MESSAGE:
                IOUtils.writeUTF(os, command.getData(0));
                IOUtils.writeUTF(os, command.getData(1));
                break;
            case MOVE:
                PlayerId.serialize(os, command.getData(0));
                Move.serialize(os, command.getData(1));
                break;
        }
    }
    
    public static final Command unserialize(InputStream is) throws IOException
    {
        CommandType type = CommandType.valueOf(IOUtils.readSignedInt32(is));
        if(type == null)
            return Command.INVALID;
        switch(type)
        {
            case CHAT_MESSAGE: {
                String username = IOUtils.readUTF(is);
                String msg = IOUtils.readUTF(is);
                return Command.chatMessage(username, msg);
            }
            case MOVE: {
                PlayerId player = PlayerId.unserialize(is);
                Move move = Move.unserialize(is);
                return Command.move(player, move);
            }
        }
        return Command.INVALID;
    }
}
