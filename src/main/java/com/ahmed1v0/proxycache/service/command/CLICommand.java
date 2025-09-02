package com.ahmed1v0.proxycache.service.command;

public interface CLICommand {
    String getCommandName();
    void execute(String ...args);
}
