package com.pattillo.model;

public class TestMessage {
    private String guild;
    private String channel;
    private String message;

    public TestMessage() {
    }

    public TestMessage(String guild, String channel, String message) {
        this.guild = guild;
        this.channel = channel;
        this.message = message;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
