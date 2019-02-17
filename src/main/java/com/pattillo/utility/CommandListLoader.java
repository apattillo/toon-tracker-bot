package com.pattillo.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pattillo.model.CommandList;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class CommandListLoader {
    public static CommandList load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new ClassPathResource("commands.json").getFile(), CommandList.class);
    }
}
