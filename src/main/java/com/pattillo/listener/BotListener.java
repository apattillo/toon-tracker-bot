package com.pattillo.listener;

import com.pattillo.service.CommandService;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotListener extends ListenerAdapter {
    private CommandService commandService;

    @Autowired
    public BotListener(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        if (shouldIgnoreMessage(event)) {
            return;
        }

        Message message = event.getMessage();
        String content = message.getContentRaw();

        String response;
        try {
            response = commandService.handle(content);
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
