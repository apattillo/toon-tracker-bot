package com.pattillo.handlers;

import com.pattillo.entity.ToonInfo;
import com.pattillo.repository.ToonInfoRepo;
import com.pattillo.utility.MemberUtility;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateToonHandler implements CommandHandler {

    @Autowired
    private ToonInfoRepo toonInfoRepo;

    @Override
    public String handle(String commandString, MessageReceivedEvent event) {
        String response;

        // strip the first part from the command string (!updateToon)
        String commandArgs = commandString.substring(commandString.indexOf(' ') + 1);

        // the rest of the string contains the arguments
        String[] splitCommand = commandArgs.split(COMMAND_ARG_DELIMITER);
        String toonName = splitCommand[0];

        ToonInfo existingToon = toonInfoRepo.getToonByName(toonName);
        if (existingToon == null) {
            response = "`Toon not found`";
        } else if (MemberUtility.isValidOwner(existingToon.getOwnerId(), event)) {
            String level = splitCommand[1];
            if (MemberUtility.isValidLevel(level)) {
                ToonInfo toonInfo = new ToonInfo();
                toonInfo.setToonName(toonName);
                toonInfo.setToonLevel(level);

                toonInfoRepo.updateToon(toonInfo);

                response = String.format("`Updated %s to level %s`", toonInfo.getToonName(), toonInfo.getToonLevel());
            } else {
                response = "`Invalid level`";
            }
        } else {
            response = String.format("`You must be the owner of %s to update`", toonName);
        }

        return response;
    }
}
