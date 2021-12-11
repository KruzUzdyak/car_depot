package com.epam.volodko.servlet;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider(){

    }

    public Command getCommand(String commandName){
        return commands.get(commandName);
    }
}
