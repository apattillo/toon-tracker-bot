package com.pattillo.handlers;

import com.pattillo.entity.Build;
import com.pattillo.repository.BuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveBuildHandler implements CommandHandler {

    @Autowired
    private BuildRepository buildRepository;

    @Override
    public String handle(String commandString) {
        // strip the first part from the command string (!saveBuild)
        String commandArgs = commandString.substring(commandString.indexOf(' ') + 1);

        // the rest of the string contains the arguments, split them up by comma
        String[] splitCommand = commandArgs.split(",");

        // dirty - just saving build name, build link.. can clean this up with a json input rather than fixed fields
        Build buildObject = new Build();
        buildObject.setName(splitCommand[0].trim());
        buildObject.setLink(splitCommand[1].trim());

        buildRepository.save(buildObject);

        return String.format("`Saved build - Name: %s, Link:` <%s>", buildObject.getName(), buildObject.getLink());
    }
}
