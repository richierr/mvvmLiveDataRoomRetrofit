package com.sandbox.rader.app;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandbox.rader.api.Connection;
import com.sandbox.rader.room_database.RaderDataBase;
import com.sandbox.rader.shared_prefs.StorageManager;
import com.sandbox.rader.shared_prefs.MyPreferences;
import com.sandbox.rader.user.UserManager;
import com.sandbox.rader.user.UserManagerImpl;


public class App extends Application {

    private static App instance;
    private Connection connection;
    private static RaderDataBase databaseInstance;
    private StorageManager preferenceManager;
    private static MyPreferences preferences;
    private Gson gson;
    private static UserManager userManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        databaseInstance = RaderDataBase.getDatabase(this);
        preferenceManager = new StorageManager(this);
        preferences = new MyPreferences(preferenceManager, gson);
        userManager = new UserManagerImpl();
    }

    public static App getInstance(){
        return instance;
    }

    public static UserManager getUserManager(){
        return userManager;
    }

    public MyPreferences getPrefs() {
        return preferences;
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = new Connection(gson);
        }
        return connection;
    }

    public RaderDataBase getDatabase() {
        return databaseInstance;
    }

}
