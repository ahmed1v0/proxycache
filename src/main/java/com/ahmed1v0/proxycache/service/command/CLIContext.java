package com.ahmed1v0.proxycache.service.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CLIContext implements CommandLineRunner {

    private final Map<String, CLICommand> commandMap = new HashMap<>();

    public CLIContext(List<CLICommand> commands) {
        for (CLICommand cmd : commands) {
            commandMap.put(cmd.getCommandName(), cmd);
        }
    }

    @Override
    public void run(String... args) {
        if (args.length == 0) return;

        for (String arg : args) {
            CLICommand command = commandMap.get(arg);
            if (command != null) {
                command.execute(args);
//                return;
            }
        }

        System.out.println("Unknown command. Usage: --port <port> --origin <url> OR list");
    }
}

