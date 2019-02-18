package com.pattillo.handlers;

import com.pattillo.entity.Build;
import com.pattillo.repository.BuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBuildHandler implements CommandHandler {

    @Autowired
    private BuildRepository buildRepository;

    @Override
    public String handle(String commandString) {
        // strip the first part from the command string (!deleteBuild)
        String buildName = commandString.substring(commandString.indexOf(' ') + 1);

        Build buildObject = new Build();
        buildObject.setName(buildName);

        buildRepository.delete(buildObject);

        return String.format("`Removed build - %s`", buildObject.getName());
    }
}
