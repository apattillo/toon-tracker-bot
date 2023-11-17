package com.pattillo.handlers;

import com.pattillo.entity.ToonInfo;
import com.pattillo.repository.ToonInfoRepo;
import com.pattillo.utility.MemberUtility;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveToonHandler implements CommandHandler {

    @Autowired
    private ToonInfoRepo toonInfoRepo;

    @Override
    public String handle(String commandString, MessageReceivedEvent event) {
        String response = null;

        // strip the first part from the command string (!addToon)
        String commandArgs = commandString.substring(commandString.indexOf(' ') + 1);

        // the rest of the string contains the arguments
        String[] splitCommand = commandArgs.split(COMMAND_ARG_DELIMITER);

        String ownerIdRaw = splitCommand[0];
        if (MemberUtility.isValidOwner(ownerIdRaw, event)) {
            String ownerUserName = null;
            if (ownerIdRaw.equals("Shared")) {
                ownerUserName = ownerIdRaw;
            } else {
                Member owner = event.getGuild().getMemberById(MemberUtility.getIdFromRaw(ownerIdRaw));
                if (owner != null) {
                    ownerUserName = owner.getEffectiveName();
                } else {
                    response = "`Can't find owner in cache`";
                }
            }

            String level = splitCommand[3];

            if (response == null && !MemberUtility.isValidLevel(level)) {
                response = "`Invalid level`";
            }

            String race = splitCommand[2];

            if (response == null && !MemberUtility.isValidRace(race)) {
                response = "`Invalid race - use the 3 letter race code (BAR, DEF, DWF, etc)`";
            }

            String toonClass = splitCommand[3];

            if (response == null && !MemberUtility.isValidClass(toonClass)) {
                response = "`Invalid class: " + toonClass;
            }

            if (response == null) {
                ToonInfo toonInfo = new ToonInfo();
                toonInfo.setOwnerId(ownerIdRaw);
                toonInfo.setOwnerName(ownerUserName);
                toonInfo.setToonName(splitCommand[1]);
                toonInfo.setToonRace(race.toUpperCase());
                toonInfo.setToonClass(toonClass);
                toonInfo.setToonLevel(level);

                toonInfoRepo.addToon(toonInfo);

                response = String.format("`Added %s`", toonInfo.getToonName());
            }
        } else {
            response = "`Invalid owner, use your @Name or Shared`";
        }

        return response;
    }
}
