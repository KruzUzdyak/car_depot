package com.epam.volodko.dao.database;

import java.util.ResourceBundle;

public class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();

//todo узнать насчёт расположения для getBundle()
    private final ResourceBundle bundle = ResourceBundle.getBundle("db.properties");

    private DBResourceManager() {
    }

    public static DBResourceManager getInstance(){
        return instance;
    }

    public String getValue(String key){
        return bundle.getString(key);
    }
}
