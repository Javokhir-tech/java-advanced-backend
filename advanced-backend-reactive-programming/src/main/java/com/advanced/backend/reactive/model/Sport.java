package com.advanced.backend.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("sport")
public class Sport {
    @Id
    private Integer id;
    private String name;

    public Sport() {}
    public Sport(String name) { this.name = name; }
    public Sport(Integer id, String name) { this.id = id; this.name = name; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
