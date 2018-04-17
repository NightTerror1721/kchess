/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import kp.games.kchess.board.Board;
import kp.games.kchess.board.PlayerId;
import static kp.games.kchess.board.PlayerId.BLACK;
import static kp.games.kchess.board.PlayerId.WHITE;

/**
 *
 * @author Asus
 */
public final class GameCore
{
    private final Pipeline[] pipelines;
    private final Board board;
    private PlayerId playerTurn;
    
    public GameCore(Board board, Pipeline white, Pipeline black)
    {
        this.board = Objects.requireNonNull(board);
        this.pipelines = new Pipeline[] {
            Objects.requireNonNull(white),
            Objects.requireNonNull(black)
        };
    }
    
    private Pipeline pipeline(PlayerId player) { return pipelines[player.ordinal()]; }
    
    public final void start()
    {
        
    }
    
    private void parseCommand(PlayerId player, Command command)
    {
        CommandType type = command.getCommandType();
        switch(type)
        {
            case CHAT_MESSAGE: {
                sendChatMessage(player, player, command.getData(0));
                sendChatMessage(player, player.inverse(), command.getData(0));
            } break;
            case MOVE: {
                if(player != playerTurn)
                    break;
                Move move = command.getData(0);
                if(move.getPlayer() != player)
                    break;
                LinkedList<String> log = new LinkedList<>();
                MoveState ms = board.applyMove(move, log);
                if(ms != null)
                {
                    playerTurn = playerTurn.inverse();
                    sendBroadcast(Command.moveResult(ms, board.createSnapshot()));
                    sendBroadcastChatMessages(log);
                    sendBroadcast(Command.playerTurn(playerTurn));
                }
            } break;
        }
    }
    
    private void sendChatMessage(PlayerId from, PlayerId to, String text)
    {
        Command msg = Command.chatMessage(from.getStringCode(), text);
        pipeline(to).sendCommand(msg);
    }
    
    private void sendBroadcastChatMessage(String text)
    {
        Command msg = Command.chatMessage("INFO", text);
        sendBroadcast(msg);
    }
    
    private void sendBroadcastChatMessages(List<String> msgs)
    {
        msgs.forEach((text) -> {
            sendBroadcastChatMessage(text);
        });
    }
    
    private void sendBroadcast(Command command)
    {
        pipeline(WHITE).sendCommand(command);
        pipeline(BLACK).sendCommand(command);
    }
}
