package com.pattillo.handlers;

import com.pattillo.entity.ToonInfo;
import com.pattillo.repository.ToonInfoRepo;
import com.pattillo.utility.MemberUtility;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteToonHandler implements CommandHandler {

    @Autowired
    private ToonInfoRepo toonInfoRepo;

    @Override
    public String handle(String commandString, MessageReceivedEvent event) {
        String response;

        // strip the first part from the command string (!deleteToon)
        String commandArgs = commandString.substring(commandString.indexOf(' ') + 1);

        // the rest of the string contains the arguments
        String[] splitCommand = commandArgs.split(COMMAND_ARG_DELIMITER);
        String toonName = splitCommand[0];

        ToonInfo existingToon = toonInfoRepo.getToonByName(toonName);
        if (existingToon == null) {
            response = "`Toon not found`";
        } else if (MemberUtility.isValidOwner(existingToon.getOwnerId(), event)) {
            toonInfoRepo.deleteToonByName(toonName);
            response = String.format("`Deleted %s`", toonName);
        } else {
            response = String.format("`You must be the owner of %s to delete`", toonName);
        }

        return response;
    }
}
