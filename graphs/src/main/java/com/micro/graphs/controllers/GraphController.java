package com.micro.graphs.controllers;

import com.micro.graphs.services.GraphService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graphs")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping(value = "/findAll")
    public Graph findAllCustom() {
        return graphService.findAllCustom();
    }

}
