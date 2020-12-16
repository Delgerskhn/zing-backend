package com.example.Zing.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="first_name")
    private String first_name;
    @Column(name="last_name")
    private String last_name;
    @Column(name="picture_url")
    private String picture_url;
    @Column(name="fbid")
    private String fbid;
    @Column(name="email")
    private String email;
    @OneToMany( cascade = CascadeType.ALL)
    private List<Template> templates;

    public void addTemplate(Template template) {
        this.templates.add(template);
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fb_id) {
        this.fbid = fb_id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
