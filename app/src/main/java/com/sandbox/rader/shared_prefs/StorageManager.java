package com.sandbox.rader.shared_prefs;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class StorageManager {

    private final SharedPreferences mPreferences;


    public StorageManager(Application application) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

    public String getString(String key, String defaultValue) {
        return mPreferences.getString(key, defaultValue);
    }

    void putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    void removePref(String key) {
        mPreferences.edit().remove(key).apply();
    }
}
