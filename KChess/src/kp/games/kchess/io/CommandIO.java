/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kp.games.kchess.board.BoardSnapshot;
import kp.games.kchess.board.PlayerId;
import kp.games.kchess.core.Command;
import kp.games.kchess.core.CommandType;
import kp.games.kchess.core.EndgameState;
import kp.games.kchess.core.Move;
import kp.games.kchess.core.MoveState;

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
                Move.serialize(os, command.getData(0));
                break;
            case MOVE_RESULT:
                MoveState.serialize(os, command.getData(0));
                BoardSnapshot.serialize(os, command.getData(1));
                break;
            case PLAYER_TURN:
                PlayerId.serialize(os, command.getData(0));
                break;
            case ENDGAME:
                EndgameState.serialize(os, command.getData(0));
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
                Move move = Move.unserialize(is);
                return Command.move(move);
            }
            case MOVE_RESULT: {
                MoveState ms = MoveState.unserialize(is);
                BoardSnapshot currentState = BoardSnapshot.unserialize(is);
                return Command.moveResult(ms, currentState);
            }
            case PLAYER_TURN: {
                PlayerId player = PlayerId.unserialize(is);
                return Command.playerTurn(player);
            }
            case ENDGAME: {
                EndgameState state = EndgameState.unserialize(is);
                return Command.endgame(state);
            }
        }
        return Command.INVALID;
    }
}
