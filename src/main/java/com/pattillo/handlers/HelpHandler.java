package com.pattillo.handlers;

import com.pattillo.model.Command;
import com.pattillo.model.CommandList;
import com.pattillo.utility.CommandListLoader;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class HelpHandler implements CommandHandler {
    @Override
    public String handle(String commandString, MessageReceivedEvent event) {
        StringBuilder sb = new StringBuilder();
        CommandList commandList = null;
        try {
            commandList = CommandListLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("```Help:\n");

        if (null != commandList) {
            for (Command command : commandList.getCommands()) {
                if (command.getDescription().contains("Usage:")) {
                    // put Usage example on its own line
                    String firstLine = command.getDescription().substring(0, command.getDescription().indexOf("Usage:"));
                    String secondLine = command.getDescription().substring(command.getDescription().indexOf("Usage:"));
                    sb.append(String.format("\t%s: %s\n", command.getName(), firstLine));
                    sb.append(String.format("\t\t%s\n", secondLine));
                } else {
                    sb.append(String.format("\t%s: %s\n", command.getName(), command.getDescription()));
                }
            }
        }

        sb.append("```");

        return sb.toString();
    }
}
