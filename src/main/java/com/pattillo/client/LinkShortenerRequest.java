package com.pattillo.client;

import java.io.Serializable;

public class LinkShortenerRequest implements Serializable {
    private String long_url;

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }
}
