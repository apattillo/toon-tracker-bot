package com.pattillo.handlers;

import org.springframework.stereotype.Component;

@Component
public class PingHandler implements CommandHandler {
    @Override
    public String handle(String commandString) {
        return "`pong`";
    }
}
