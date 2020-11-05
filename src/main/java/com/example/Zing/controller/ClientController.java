package com.example.Zing.controller;

import com.example.Zing.model.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user/")
public class ClientController {
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Autowired
    public ClientController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("getToken/{fb_exchange_token}")
    public AccessToken getToken(@PathVariable(value="fb_exchange_token") String fb_exchange_token) {
        String url = "https://graph.facebook.com/v8.0/oauth/access_token";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("fb_exchange_token", fb_exchange_token)
                .queryParam("grant_type", "fb_exchange_token")
                .queryParam("client_id", env.getProperty("fb.client_id"))
                .queryParam("client_secret", env.getProperty("fb.client_secret"));
        ResponseEntity<AccessToken> response = this.restTemplate.getForEntity(builder.toUriString(), AccessToken.class);
        return response.getBody();
    }

}
