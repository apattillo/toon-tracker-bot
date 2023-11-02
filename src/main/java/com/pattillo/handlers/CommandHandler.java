package com.pattillo.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface CommandHandler {
    String COMMAND_ARG_DELIMITER = "\\s+";

    String handle(String commandString, MessageReceivedEvent event);
}
