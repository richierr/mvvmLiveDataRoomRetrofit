package com.sandbox.rader.shared_prefs;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.sandbox.rader.model.User;

public class MyPreferences {

    private static final String USER_MODEL = "user_model";

    private final StorageManager storageManager;
    private final Gson gson;

    public MyPreferences(StorageManager storageManager, Gson gson) {
        this.storageManager = storageManager;
        this.gson = gson;
    }

    public void saveUser(User user) {
        String serializedUser = gson.toJson(user);
        storageManager.putString(USER_MODEL, serializedUser);
    }

    public User readUser() {
        User retUserData = null;
        String serializedUser = storageManager.getString(USER_MODEL, "");
        if (!TextUtils.isEmpty(serializedUser)) {
            retUserData = gson.fromJson(serializedUser, User.class);
        }
        return retUserData;
    }
    public void removeUser() {
        storageManager.removePref(USER_MODEL);
    }

}
