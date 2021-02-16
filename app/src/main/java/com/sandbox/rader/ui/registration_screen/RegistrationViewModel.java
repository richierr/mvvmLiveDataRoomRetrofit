package com.sandbox.rader.ui.registration_screen;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.sandbox.rader.repository.UserRepository;

public class RegistrationViewModel extends ViewModel {

    public UserRepository userRepository = UserRepository.getInstance();

    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public void registerUser() {
        userRepository.registerUser(userName.get(), password.get());
    }

}