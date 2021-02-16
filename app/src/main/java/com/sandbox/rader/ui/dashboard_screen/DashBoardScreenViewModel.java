package com.sandbox.rader.ui.dashboard_screen;

import androidx.lifecycle.ViewModel;

import com.sandbox.rader.api.livaData.CurrenciesLiveData;
import com.sandbox.rader.repository.UserRepository;

public class DashBoardScreenViewModel extends ViewModel {

    public CurrenciesLiveData currenciesLiveData = new CurrenciesLiveData();

    public UserRepository userRepository = UserRepository.getInstance();

    public void logout() {
        userRepository.logout();
    }

}