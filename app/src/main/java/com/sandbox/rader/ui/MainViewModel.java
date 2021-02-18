package com.sandbox.rader.ui;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.sandbox.rader.api.livaData.CurrenciesLiveData;
import com.sandbox.rader.repository.UserRepository;

public class MainViewModel extends ViewModel {

    public UserRepository userRepository = UserRepository.getInstance();

    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    //LOGIN
    public void login() {

        userRepository.login(userName.get(), password.get());
    }

    //REGISTER

    public void registerUser() {
        userRepository.registerUser(userName.get(), password.get());
    }

    //DASHBOARD
    public CurrenciesLiveData currenciesLiveData = new CurrenciesLiveData();



    public void logout() {
        userRepository.logout();
    }


}
