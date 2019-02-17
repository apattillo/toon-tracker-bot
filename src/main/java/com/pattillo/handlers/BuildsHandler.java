package com.pattillo.handlers;

import com.pattillo.entity.Build;
import com.pattillo.repository.BuildRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildsHandler implements CommandHandler {

    private BuildRepository buildRepository;

    public BuildsHandler(BuildRepository buildRepository) {
        this.buildRepository = buildRepository;
    }

    @Override
    public String handle(String commandString) {
        Iterable<Build> builds = buildRepository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("`Builds:`\n");
        for (Build build : builds) {
            sb.append(String.format("\t`%s:` <%s>\n", build.getName(), build.getLink()));
        }

        return sb.toString();
    }
}
