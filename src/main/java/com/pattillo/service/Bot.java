package com.pattillo.service;

import com.pattillo.listener.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bot implements DiscordBot {
    private static final String SECRET_TOKEN = "";
    private JDA jda;

    @Autowired
    public Bot(BotListener botListener) {
        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(SECRET_TOKEN)
                    .addEventListener(botListener)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String guild, String channel, String message) {
        try {
            jda.getGuildById(guild)
                    .getTextChannelById(channel)
                    .sendMessage(message)
                    .queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
