package com.ahmed1v0.proxycache.service.command;

import com.ahmed1v0.proxycache.entity.ProxyEntry;
import com.ahmed1v0.proxycache.repository.ProxyEntryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListCommand implements CLICommand{
    private final ProxyEntryRepository repo;

    ListCommand(ProxyEntryRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getCommandName() {
        return "list";
    }
    @Override
    public void execute(String ...args) {
        List<ProxyEntry> entries = repo.findAll();
        for (ProxyEntry entry : entries) {
            System.out.println(entry);
        }
    }
}
