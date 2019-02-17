package com.pattillo.handlers;

import com.pattillo.entity.Guide;
import com.pattillo.repository.GuideRepository;
import org.springframework.stereotype.Service;

@Service
public class GuidesHandler implements CommandHandler {

    private GuideRepository guideRepository;

    public GuidesHandler(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public String handle(String commandString) {
        Iterable<Guide> guides = guideRepository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("`Guides:`\n");
        for (Guide guide : guides) {
            sb.append(String.format("\t`%s:` <%s>\n", guide.getName(), guide.getLink()));
        }

        return sb.toString();
    }
}
