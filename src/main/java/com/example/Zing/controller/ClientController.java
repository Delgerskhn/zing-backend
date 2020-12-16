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

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
        AccessToken accessToken = facebookService.longLivedToken(fb_exchange_token);

        return accessToken;
    }

    @PostMapping("register")
    public ResponseEntity<Client> registerClient(HttpServletRequest servletRequest, @RequestBody Client client) {
        Client user = clientRepository.findClientByFbid(client.getFbid());
        if(user!=null) {
            throw new EntityExistsException("Client registered already!");
        }
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @PostMapping("addTemplate")
    public ResponseEntity<Client> addTemplate(HttpServletRequest servletRequest, @RequestBody Template template) throws ResourceNotFoundException {
        String userId = (String) servletRequest.getAttribute("user_id");
        Client client = clientRepository.findClientByFbid(userId);
        if(client==null) throw new ResourceNotFoundException("User not found");
        client.addTemplate(template);
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @GetMapping("getTemplates")
    public List<Template> getTemplates(
            HttpServletRequest servletRequest) throws ResourceNotFoundException {
        String userId = (String) servletRequest.getAttribute("user_id");
        System.out.println(userId);
        Client client = clientRepository.findClientByFbid(userId);
        if(client==null) throw new ResourceNotFoundException("User not found");
        return client.getTemplates();
    }

}
