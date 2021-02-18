package com.sandbox.rader.ui.dashboard_screen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sandbox.rader.R;
import com.sandbox.rader.databinding.DashboardScreenFragmentBinding;
import com.sandbox.rader.ui.MainActivity;
import com.sandbox.rader.ui.MainViewModel;
import com.sandbox.rader.utils.Constants;

public class DashBoardScreenFragment extends Fragment {

    //public DashBoardScreenViewModel mViewModel;

    public MainViewModel mainViewModel;

    private DashboardScreenFragmentBinding binding;

    private MainActivity mainActivity;

    public static DashBoardScreenFragment newInstance() {
        return new DashBoardScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.dashboard_screen_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel=mainActivity.getMainViewModel();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setViewmodel(mainViewModel);

//        mViewModel = new ViewModelProvider(this).get(DashBoardScreenViewModel.class);
//        binding.setViewmodel(mViewModel);

        observeLogin();
        fetchExchangeRates();
    }

    private void fetchExchangeRates() {

        mainViewModel.userRepository.fetchExchangeRates(mainViewModel.currenciesLiveData);
        //mViewModel.userRepository.fetchExchangeRates(mViewModel.currenciesLiveData);
    }

    private void observeLogin() {

        mainViewModel.userRepository.userMutableLiveData.observe(getViewLifecycleOwner(), user -> {
            if (user == null) {
                mainActivity.navigateToScreen(Constants.LOGIN_SCREEN);
            }

        });


//        mViewModel.userRepository.userMutableLiveData.observe(getViewLifecycleOwner(), user -> {
//            if (user == null) {
//                mainActivity.navigateToScreen(Constants.LOGIN_SCREEN);
//            }
//
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mainViewModel.userRepository.userMutableLiveData.removeObservers(getViewLifecycleOwner());
        //mViewModel.userRepository.userMutableLiveData.removeObservers(getViewLifecycleOwner());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;

        }
    }
}