package org.orb.cli.Models;

public class IndexRequest {
    private String name;

    public IndexRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
