package com.pattillo.service;

import com.pattillo.handlers.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    @Autowired
    private HelpHandler helpHandler;

    @Autowired
    private PingHandler pingHandler;

    @Autowired
    private SaveToonHandler saveToonHandler;

    @Autowired
    private UpdateToonHandler updateToonHandler;

    @Autowired
    private GetToonsHandler getToonsHandler;

    @Autowired
    private DeleteToonHandler deleteToonHandler;

    public String handle(String commandString, MessageReceivedEvent event) {
        String response;

        if (commandString.startsWith("!ping")) {
            response = pingHandler.handle(commandString, event);
        } else if (commandString.startsWith("!help")) {
            response = helpHandler.handle(commandString, event);
        } else if (commandString.startsWith("!toons")) {
            response = getToonsHandler.handle(commandString, event);
        } else if (commandString.startsWith("!addToon")) {
            response = saveToonHandler.handle(commandString, event);
        } else if (commandString.startsWith("!updateToon")) {
            response = updateToonHandler.handle(commandString, event);
        } else if (commandString.startsWith("!deleteToon")) {
            response = deleteToonHandler.handle(commandString, event);
        } else {
            response = "I don't know what that is";
        }

        return response;
    }
}
