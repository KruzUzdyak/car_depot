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
        commands.put(CommandName.GO_TO_ALL_ORDERS_PAGE, new GoToAllOrdersPageCommand());
        commands.put(CommandName.GO_TO_ORDER_INFO_PAGE, new GoToOrderInfoPageCommand());
        commands.put(CommandName.GO_TO_USER_CABINET_PAGE, new GoToUserCabinetPage());
    }

    public Command getCommand(String commandName){
        return commands.get(commandName);
    }
}
