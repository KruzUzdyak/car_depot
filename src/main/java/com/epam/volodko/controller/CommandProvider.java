package com.epam.volodko.controller;

import com.epam.volodko.controller.command_impl.*;
import com.epam.volodko.controller.constant.CommandName;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider(){
        commands.put(CommandName.GO_TO_INITIAL_PAGE, new GoToInitialPageCommand());
        commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistrationCommand());
        commands.put(CommandName.GO_TO_LOGINATION, new GoToLoginationCommand());
        commands.put(CommandName.LOGINATION, new LoginationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandName.LOCALIZATION, new LocalizationCommand());
    }

    public Command getCommand(String commandName){
        return commands.get(commandName);
    }
}
