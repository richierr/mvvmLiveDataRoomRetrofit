package com.sandbox.rader.user;

import com.sandbox.rader.model.User;

public interface UserManager {

     void startSession(User user);

     void endSession();

     User getUser();

     boolean isLoggedIn();

}
