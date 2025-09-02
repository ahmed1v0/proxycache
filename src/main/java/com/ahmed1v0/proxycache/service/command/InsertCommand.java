package com.ahmed1v0.proxycache.service.command;

import com.ahmed1v0.proxycache.entity.ProxyEntry;
import com.ahmed1v0.proxycache.service.ProxyService;
import org.springframework.stereotype.Component;

@Component
public class InsertCommand implements CLICommand{

    private final ProxyService proxyService;
    private Integer port;
    private String host;

    public InsertCommand(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Override
    public String getCommandName() {
        return "--port";
    }
    @Override
    public void execute(String ...args) {
        if(args.length < 1) {
            printHelp();
            return;
        }
        for(int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--port":
                    if(i+1 < args.length) {
                        port = Integer.parseInt(args[++i]);
                    }
                    break;
                case "--origin":
                    if(i+1 < args.length) {
                        host = args[++i];
                    }
                    break;
            }
        }
        try{
            ProxyEntry saved = proxyService.saveProxy(port,host);
            System.out.println("Saved entry: " + saved.getOrigin() + ":" + saved.getPort());
        }
        catch (IllegalArgumentException e){
            System.out.println("Error in insert command" + e.getMessage());
            printHelp();
        }
    }
    public void printHelp() {
       System.out.println("Usage: java insert --port <port> --origin <origin>");
       System.out.println("Example: java insert --port 8080 --origin https://github.com/ahmed1v0/proxycache");
    }

}
