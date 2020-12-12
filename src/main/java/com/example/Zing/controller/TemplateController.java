package com.example.Zing.controller;

import com.example.Zing.exception.ResourceNotFoundException;
import com.example.Zing.model.Template;
import com.example.Zing.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/template/")
public class TemplateController {
    @Autowired
    private TemplateRepository templateRepository;
    @PostMapping("add")
    public Template createTemplate(@RequestBody Template template) throws ResourceNotFoundException {
        Template newTemplate = this.templateRepository.save(template);
        return newTemplate;
    }
    @PostMapping("update/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable(value="id") Integer templateId, @Valid @RequestBody Template updateTemplate) throws ResourceNotFoundException {
        Template template = this.templateRepository.findById(templateId).orElseThrow(()->new ResourceNotFoundException("Not found"));
        template.setDomains(updateTemplate.getDomains());
        template.setExcludeDomains(updateTemplate.getExcludeDomains());
        template.setFrom(updateTemplate.getFrom());
        template.setQuery(updateTemplate.getQuery());
        template.setQueryInTitle(updateTemplate.getQueryInTitle());
        template.setSortBy(updateTemplate.getSortBy());
        template.setSources(updateTemplate.getSources());
        template.setTo(updateTemplate.getTo());

//        book.setAuthor(bookDetails.getAuthor());
//        book.setName(bookDetails.getName());
//
        return ResponseEntity.ok(this.templateRepository.save(template));
    }
}
