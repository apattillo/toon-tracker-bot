package com.pattillo.client;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LinkShortener {
    private static final String API_KEY = "";
    private static final String BASE_URL = "https://api-ssl.bitly.com/v4/shorten";

    public String shorten(String longUrl) {
        String shortUrl;
        LinkShortenerRequest requestBody = new LinkShortenerRequest();
        requestBody.setLong_url(longUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + API_KEY);

        HttpEntity<LinkShortenerRequest> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<LinkShortenerResponse> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, entity, LinkShortenerResponse.class);
            shortUrl = response.getBody().getId();

            if (!shortUrl.startsWith("http")) {
                shortUrl = "http://" + shortUrl;
            }
        } catch (Exception e) {
            System.out.println("Call to link shortener API failed");
            shortUrl = longUrl;
        }

        return shortUrl;
    }
}
