package com.sandbox.rader.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.sandbox.rader.R;
import com.sandbox.rader.app.App;
import com.sandbox.rader.repository.UserRepository;
import com.sandbox.rader.ui.dashboard_screen.DashBoardScreenFragment;
import com.sandbox.rader.ui.login_screen.LoginFragment;
import com.sandbox.rader.ui.registration_screen.RegistrationFragment;
import com.sandbox.rader.utils.Constants;

public class MainActivity extends AppCompatActivity {

    //VIEWMODEL
    public MainViewModel mainViewModel;


    public UserRepository userRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        userRepository = UserRepository.getInstance();
        if (App.getUserManager().isLoggedIn()) {
            navigateToScreen(Constants.DASHBOARD_SCREEN);
        } else {
            navigateToScreen(Constants.LOGIN_SCREEN);
        }
    }


    public void navigateToScreen(int screenType) {
        Fragment fragment;
        switch (screenType) {
            case Constants.LOGIN_SCREEN:
                fragment = new LoginFragment();
                break;
            case Constants.REGISTRATION_SCREEN:
                fragment = new RegistrationFragment();
                break;
            case Constants.DASHBOARD_SCREEN:
            default:
                fragment = new DashBoardScreenFragment();
                break;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }


    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }
}
