package com.example.Zing.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="template")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="query")
    private String query;
    @Column(name="query_in_title")
    private String queryInTitle;
    @Column(name="sources")
    private String sources;
    @Transient
    private List<Source> sourceList;
    @Column(name="domains")
    private String domains;
    @Column(name="exclude_domains")
    private String excludeDomains;
    @Column(name="date_from")
    private Date from;
    @Column(name="date_to")
    private Date to;
    @Column(name="sort_by")
    private String sortBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQueryInTitle() {
        return queryInTitle;
    }

    public void setQueryInTitle(String queryInTitle) {
        this.queryInTitle = queryInTitle;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getExcludeDomains() {
        return excludeDomains;
    }

    public void setExcludeDomains(String excludeDomains) {
        this.excludeDomains = excludeDomains;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
