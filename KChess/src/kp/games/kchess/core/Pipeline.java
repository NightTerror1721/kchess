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
public interface Pipeline
{
    boolean hasMoreReceivedCommands();
    Command popReceivedCommand();
    
    void sendCommand(Command command);
}
