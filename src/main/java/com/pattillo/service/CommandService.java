package com.pattillo.service;

import com.pattillo.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    @Autowired
    private BuildsHandler buildsHandler;

    @Autowired
    private GuidesHandler guidesHandler;

    @Autowired
    private HelpHandler helpHandler;

    @Autowired
    private PingHandler pingHandler;

    @Autowired
    private SaveBuildHandler saveBuildHandler;

    @Autowired
    private SaveGuideHandler saveGuideHandler;

    public String handle(String commandString) throws InstantiationException, IllegalAccessException {
        String response;

        if (commandString.startsWith("!ping")) {
            response = pingHandler.handle(commandString);
        } else if (commandString.startsWith("!help")) {
            response = helpHandler.handle(commandString);
        } else if (commandString.startsWith("!builds")) {
            response = buildsHandler.handle(commandString);
        } else if (commandString.startsWith("!guides")) {
            response = guidesHandler.handle(commandString);
        } else if (commandString.startsWith("!saveBuild")) {
            response = saveBuildHandler.handle(commandString);
        } else if (commandString.startsWith("!saveGuide")) {
            response = saveGuideHandler.handle(commandString);
        } else {
            response = "I don't know what that is";
        }

        return response;
    }
}
