package com.epam.volodko.service;

import com.epam.volodko.service.command_impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(CommandName.GO_TO_LOGINATION, new GoToLoginationCommand());
        commands.put(CommandName.GO_TO_MAIN, new GoToMainCommand());
        commands.put(CommandName.LOGINATION, new LoginationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
    }

    public Command getCommand(String commandName){
        return commands.get(commandName);
    }
}
