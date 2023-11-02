package com.pattillo.handlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class PingHandler implements CommandHandler {
    @Override
    public String handle(String commandString, MessageReceivedEvent event) {
        return "`pong`";
    }
}
