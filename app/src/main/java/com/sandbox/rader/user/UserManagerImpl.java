package com.sandbox.rader.user;

import com.sandbox.rader.app.App;
import com.sandbox.rader.model.User;

public class UserManagerImpl implements UserManager {

    private User user;


    @Override
    public void startSession(User userModel) {
        this.user = userModel;
        App.getInstance().getPrefs().saveUser(userModel);
    }

    @Override
    public void endSession() {
        user = null;
        App.getInstance().getPrefs().removeUser();

    }

    @Override
    public User getUser() {
        user = App.getInstance().getPrefs().readUser();
        return user;
    }

    @Override
    public boolean isLoggedIn() {
        if (user == null) {
            user = App.getInstance().getPrefs().readUser();
        }
        return (user != null);
    }
}
