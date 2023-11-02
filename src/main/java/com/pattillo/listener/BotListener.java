package com.pattillo.listener;

import com.pattillo.service.CommandService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotListener extends ListenerAdapter {
    private final CommandService commandService;

    @Autowired
    public BotListener(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (shouldIgnoreMessage(event)) {
            return;
        }

        System.out.println("Message received from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());

        Message message = event.getMessage();
        String content = message.getContentRaw();

        String response;
        try {
            response = commandService.handle(content, event);
        } catch (Exception e) {
            e.printStackTrace();
            response = "Sorry, I'm drunk";
        }

        MessageChannel channel = event.getChannel();
        channel.sendMessage(response).queue();
    }

    private boolean shouldIgnoreMessage(MessageReceivedEvent event) {
        return !isBotCommand(event) || event.getAuthor().isBot();
    }

    private boolean isBotCommand(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw().startsWith("!");
    }
}
