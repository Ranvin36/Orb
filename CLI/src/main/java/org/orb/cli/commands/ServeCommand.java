package org.orb.cli.commands;

import picocli.CommandLine.Command;

@Command(name = "serve", description = "Serves an ORB")
public class ServeCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Serving a ORB");
    }
}

