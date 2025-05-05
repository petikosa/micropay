package com.micro.graphs.controllers;

public class Relationship {

    public Long id;
    public Long from;
    public Long to;
    public RelationshipLabel label;

    public Relationship(Long id, Long from, Long to, RelationshipLabel label) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.label = label;
    }
}
