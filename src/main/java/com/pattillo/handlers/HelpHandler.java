package com.pattillo.handlers;

import com.pattillo.model.Command;
import com.pattillo.model.CommandList;
import com.pattillo.utility.CommandListLoader;
import org.springframework.stereotype.Component;

@Component
public class HelpHandler implements CommandHandler {
    @Override
    public String handle(String commandString) {
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
                sb.append(String.format("\t%s: %s\n", command.getName(), command.getDescription()));
            }
        }

        sb.append("```");

        return sb.toString();
    }
}
