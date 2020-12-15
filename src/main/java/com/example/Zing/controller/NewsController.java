package com.example.Zing.controller;

import com.example.Zing.NewsApiService;
import com.example.Zing.exception.ResourceNotFoundException;
import com.example.Zing.model.NewsResult;
import com.example.Zing.model.Template;
import com.example.Zing.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/news/")
public class NewsController {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private Environment env;
    private RestTemplate restTemplate;
    public NewsController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("search")
    public NewsResult directSearch(@RequestBody Template template) {
        return search(template);
    }

    @GetMapping("{templateId}")
    public NewsResult getEverything(@PathVariable(value="templateId") Integer templateId) throws ResourceNotFoundException {
        Template template = this.templateRepository.findById(templateId).orElseThrow(()->new ResourceNotFoundException("Template not found"));
        return search(template);
    }

    public NewsResult search(Template template) {
        String url = "https://newsapi.org/v2/everything";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if(template.getQuery()!=null) {
            builder.queryParam("q", template.getQuery());
        }
        if(template.getQueryInTitle()!=null) {
            builder.queryParam("qInTitle", template.getQueryInTitle());
        }
        if(template.getSources()!=null) {
            builder.queryParam("sources", template.getSources());
        }
        if(template.getDomains()!=null) {
            builder.queryParam("domains", template.getDomains());
        }
        if(template.getExcludeDomains()!=null) {
            builder.queryParam("excludeDomains", template.getExcludeDomains());
        }
        if(template.getFrom()!=null) {
            builder.queryParam("from", template.getFrom());
        }
        if(template.getTo()!=null) {
            builder.queryParam("to", template.getTo());
        }
        if(template.getSortBy()!=null) {
            builder.queryParam("sortBy", template.getSortBy());
        }
        builder.queryParam("pageSize", 10);
        builder.queryParam("page", 1);
        builder.queryParam("apiKey", env.getProperty("newsapi.key"));
        ResponseEntity<NewsResult> response = this.restTemplate.getForEntity(builder.toUriString(), NewsResult.class);
        return response.getBody();
    }
}
