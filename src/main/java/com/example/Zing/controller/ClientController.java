package com.example.Zing.controller;

import com.example.Zing.FacebookService;
import com.example.Zing.exception.ResourceNotFoundException;
import com.example.Zing.model.AccessToken;
import com.example.Zing.model.Client;
import com.example.Zing.model.Template;
import com.example.Zing.repository.ClientRepository;
import com.example.Zing.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class ClientController {
    private RestTemplate restTemplate;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private Environment env;
    @Autowired
    private FacebookService facebookService;
    @Autowired
    public ClientController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("getToken")
    public AccessToken getToken(@RequestHeader(value="fb_exchange_token") String fb_exchange_token) {
        return facebookService.longLivedToken(fb_exchange_token);
    }

    @GetMapping("getTemplates")
    public List<Template> getTemplates(@RequestHeader(value="Authorization") String token, @RequestHeader(value="userId") String userId) throws ResourceNotFoundException {
        Client client = clientRepository.findById(Long.parseLong(userId)).orElseThrow(()->new ResourceNotFoundException("User not found"));
        return client.getTemplates();
    }

}
