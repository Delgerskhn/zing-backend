package com.example.Zing;

import com.example.Zing.model.AccessToken;
import com.example.Zing.model.NewsResult;
import com.example.Zing.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.env.Environment;

@Service
public class NewsApiService {
    private final RestTemplate restTemplate;
    @Autowired
    private Environment env;
    public NewsApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public NewsResult getEverything(Template template, Integer pageSize, Integer page) {
            String url = "https://newsapi.org/v2/everything";
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("q", template.getQuery())
                    .queryParam("qInTitle", template.getQueryInTitle())
                    .queryParam("sources", template.getSources())
                    .queryParam("domains", template.getDomains())
                    .queryParam("excludeDomains", template.getExcludeDomains())
                    .queryParam("from", template.getFrom())
                    .queryParam("to", template.getTo())
                    .queryParam("sortBy", template.getSortBy())
                    .queryParam("pageSize", pageSize)
                    .queryParam("page", page)
                    .queryParam("apiKey", env.getProperty("newsapi.key"));
            String uri = builder.toUriString();
            ResponseEntity<NewsResult> response = this.restTemplate.getForEntity(builder.toUriString(), NewsResult.class);
            return response.getBody();
    }
}
