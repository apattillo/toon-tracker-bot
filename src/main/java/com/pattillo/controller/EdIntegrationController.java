package com.pattillo.controller;

import com.pattillo.model.TestMessage;
import com.pattillo.service.DiscordBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdIntegrationController {
    @Autowired
    DiscordBot bot;

    @PostMapping(value = "/test")
    public void testMessage(@RequestBody TestMessage message) {
        System.out.println(message);
        bot.sendMessage(message.getGuild(), message.getChannel(), message.getMessage());
    }
}
