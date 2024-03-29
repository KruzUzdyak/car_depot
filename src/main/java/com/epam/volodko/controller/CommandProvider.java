package com.epam.volodko.controller;

import com.epam.volodko.controller.impl.*;
import com.epam.volodko.controller.constant.CommandName;
import com.epam.volodko.controller.impl.cars_page.AddNewCarCommand;
import com.epam.volodko.controller.impl.cars_page.GoToAddNewCarCommand;
import com.epam.volodko.controller.impl.cars_page.GoToCarInfoPageCommand;
import com.epam.volodko.controller.impl.main_page.GoToMainPageCommand;
import com.epam.volodko.controller.impl.orders_page.*;
import com.epam.volodko.controller.impl.user_cabinet.*;

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
        commands.put(CommandName.GO_TO_CREATE_ORDER_PAGE, new GoToCreateOrderPageCommand());
        commands.put(CommandName.CREATE_ORDER, new CreateOrderCommand());
        commands.put(CommandName.ORDER_SET_ADMIN, new OrderSetAdminCommand());
        commands.put(CommandName.ORDER_SET_CAR, new OrderSetCarCommand());
        commands.put(CommandName.ORDER_COMPLETE, new OrderCompleteCommand());
        commands.put(CommandName.GO_TO_USER_CABINET_PAGE, new GoToUserCabinetPage());
        commands.put(CommandName.UPDATE_LOGIN, new GoToUpdateLoginCommand());
        commands.put(CommandName.UPDATE_LOGIN_CONFIRM, new UpdateLoginCommand());
        commands.put(CommandName.UPDATE_PASS, new GoToUpdatePassCommand());
        commands.put(CommandName.UPDATE_PASS_CONFIRM, new UpdatePassCommand());
        commands.put(CommandName.UPDATE_INFO, new GoToUpdateInfoCommand());
        commands.put(CommandName.UPDATE_INFO_CONFIRM, new UpdateInfoCommand());
        commands.put(CommandName.SAVE_LICENSE, new GoToLicenseSaveCommand());
        commands.put(CommandName.DELETE_LICENSE, new GoToLicenseDeleteCommand());
        commands.put(CommandName.DELETE_LICENSE_CONFIRM, new LicenseDeleteCommand());
        commands.put(CommandName.SAVE_LICENSE_CONFIRM, new LicenseSaveCommand());
        commands.put(CommandName.GO_TO_CAR_INFO_PAGE, new GoToCarInfoPageCommand());
        commands.put(CommandName.GO_TO_ADD_NEW_CAR, new GoToAddNewCarCommand());
        commands.put(CommandName.ADD_NEW_CAR, new AddNewCarCommand());
    }

    public Command getCommand(String commandName){
        return commands.get(commandName);
    }
}
