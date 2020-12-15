package com.example.Zing;

import com.example.Zing.model.AccessToken;
import com.example.Zing.model.UidValidResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.naming.AuthenticationException;

@Service
public class FacebookService {
    @Autowired
    private Environment env;
    private RestTemplate restTemplate;
    public FacebookService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public FacebookService() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    public AccessToken longLivedToken(String fb_exchange_token)  {
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

    public String validate(String access_token) throws AuthenticationException {
        String url = "https://graph.facebook.com/v8.0/me";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("access_token", access_token);
        ResponseEntity<UidValidResp> response;
        try{
            response = this.restTemplate.getForEntity(builder.toUriString(), UidValidResp.class);
        }catch(Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
        return response.getBody().getId();
    }
}
