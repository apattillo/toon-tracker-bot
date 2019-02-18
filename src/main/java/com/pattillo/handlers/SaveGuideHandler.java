package com.pattillo.handlers;

import com.pattillo.client.LinkShortener;
import com.pattillo.entity.Guide;
import com.pattillo.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveGuideHandler implements CommandHandler {

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private LinkShortener linkShortener;

    @Override
    public String handle(String commandString) {
        // strip the first part from the command string (!saveGuide)
        String commandArgs = commandString.substring(commandString.indexOf(' ') + 1);

        // the rest of the string contains the arguments, split them up by comma
        String[] splitCommand = commandArgs.split(",");

        // dirty - just saving name and link.. can clean this up with a json input rather than fixed fields
        Guide guideObject = new Guide();
        guideObject.setName(splitCommand[0].trim());
        guideObject.setLink(linkShortener.shorten(splitCommand[1].trim()));

        guideRepository.save(guideObject);

        return String.format("`Saved guide - Name: %s, Link:` <%s>", guideObject.getName(), guideObject.getLink());
    }
}
