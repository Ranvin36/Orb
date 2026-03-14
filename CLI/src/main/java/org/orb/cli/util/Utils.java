package org.orb.cli.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class Utils {

    private Utils() {
    }

//    Extract the name of the repository from the provided remote url
    public static String extractRepoName(String repoLink) {
        String cleaned = repoLink == null ? "" : repoLink.trim();
        if (cleaned.endsWith(".git")) {
            cleaned = cleaned.substring(0, cleaned.length() - 4);
        }
        int slashIndex = Math.max(cleaned.lastIndexOf('/'), cleaned.lastIndexOf('\\'));
        if (slashIndex >= 0 && slashIndex < cleaned.length() - 1) {
            return cleaned.substring(slashIndex + 1);
        }
        return "repository";
    }

//    Check if files already exists in the directory
    public static boolean isNotEmptyDirectory(Path path) throws IOException {
        if (!Files.isDirectory(path)) {
            return true;
        }
        try (Stream<Path> stream = Files.list(path)) {
            return stream.findAny().isPresent();
        }
    }

}
