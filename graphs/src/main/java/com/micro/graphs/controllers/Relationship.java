package com.micro.graphs.controllers;

public class Relationship {

    public Long id;
    public Long from;
    public Long to;

    public Relationship(Long id, Long from, Long to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }
}
