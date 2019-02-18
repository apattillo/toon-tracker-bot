package com.pattillo.handlers;

import com.pattillo.entity.Guide;
import com.pattillo.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteGuideHandler implements CommandHandler {

    @Autowired
    private GuideRepository guideRepository;

    @Override
    public String handle(String commandString) {
        // strip the first part from the command string (!deleteGuide)
        String guideName = commandString.substring(commandString.indexOf(' ') + 1);

        Guide guidieObject = new Guide();
        guidieObject.setName(guideName);

        guideRepository.delete(guidieObject);

        return String.format("`Removed guide - %s`", guidieObject.getName());
    }
}
