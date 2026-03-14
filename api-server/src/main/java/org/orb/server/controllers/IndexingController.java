package org.orb.server.controllers;

import org.orb.server.models.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indexing")
public class IndexingController {

    @RequestMapping("/start")
    @ResponseBody
    public String startIndexing(@RequestBody Repository repository) {
        System.out.println("Indexing process started...");
        return "Indexing process started for repository: " + repository.getName();
    }
}
