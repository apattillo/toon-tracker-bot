package com.pattillo.service;

import com.pattillo.listener.BotListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bot implements DiscordBot {
    private static final String SECRET_TOKEN = "";
    private JDA jda;

    @Autowired
    public Bot(BotListener botListener) {
        try {
            jda = JDABuilder.createDefault(SECRET_TOKEN)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .addEventListeners(botListener)
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
