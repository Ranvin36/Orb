package org.orb.server.services;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class IndexEngineService {

    public void findRepositoryInFileSystem(String repositoryName) throws IOException {
        Optional<Path> repo = locateRepositoryInFileSystem(repositoryName);
        if (repo.isPresent()) {
            System.out.println("Repository found at: " + repo.get().toString());
        } else {
            System.out.println("Repository not found in the file system.");
        }
    }

    /**
     * Locates the repository directory under the user's Documents folder.
     * Returns an Optional containing the Path if the directory exists and contains at least one entry.
     * Returns Optional.empty() if not found or on invalid input.
     */
    public Optional<Path> locateRepositoryInFileSystem(String repositoryName) throws IOException {
        if (repositoryName == null || repositoryName.isBlank()) {
            return Optional.empty();
        }

        String userHome = System.getProperty("user.home");
        if (userHome == null || userHome.isBlank()) {
            return Optional.empty();
        }

        Path repoPath = Path.of(userHome, "Documents", repositoryName);

        if (Files.exists(repoPath) && Files.isDirectory(repoPath)) {
            // Use DirectoryStream to avoid leaving an open stream
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(repoPath)) {
                if (ds.iterator().hasNext()) {
                    return Optional.of(repoPath);
                } else {
                    // directory exists but is empty -> treat as not found (matches original behavior)
                    return Optional.empty();
                }
            }
        }

        return Optional.empty();
    }

    public void startIndexing(String repoName) throws IOException {
        Optional<Path> repo = locateRepositoryInFileSystem(repoName);
        if (repo.isPresent()) {
            System.out.println("Starting indexing for: " + repo.get().toString());
            // indexing logic would go here
        } else {
            System.out.println("Cannot start indexing; repository not found: " + repoName);
        }

    }
}
