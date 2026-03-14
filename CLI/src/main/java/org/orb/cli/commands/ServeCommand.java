package org.orb.cli.commands;

import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Command(name = "serve", description = "Serves an ORB")
public class ServeCommand implements Runnable {

    @Parameters(paramLabel = "REPO_NAME",description = "Name of the repository to index")
    private String repoName;


    @Override
    public void run() {
        String home = System.getenv("USERPROFILE");
        String orbPath = Paths.get(String.valueOf(Path.of(home).resolve("documents")), "orb").toString();
        Path repoPath = Paths.get(orbPath, repoName);
        if(Files.notExists(repoPath)){
            System.err.println("Error: Repository not found in " + orbPath);
            System.err.println("Hint: Make sure to add the repository using 'orb add <REPO_LINK>' before serving.");
            return;
        }
        else{
            try (Stream<Path> stream = Files.list(repoPath)) {
                if (stream.findAny().isEmpty()) {
                    System.err.println("Error: Repository directory is empty: " + repoPath);
                    System.err.println("Hint: Make sure the repository was cloned successfully and contains files.");
                    return;
                }
            } catch (IOException e) {
                System.err.println("Error: Unable to access repository directory: " + repoPath);
                e.printStackTrace();
                return;
            }
        }
    }
}

