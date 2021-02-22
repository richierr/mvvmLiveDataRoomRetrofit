package com.sandbox.rader.ui.registration_screen;

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
import com.sandbox.rader.databinding.RegistrationFragmentBinding;
import com.sandbox.rader.ui.MainActivity;
import com.sandbox.rader.ui.MainViewModel;
import com.sandbox.rader.utils.Constants;

public class RegistrationFragment extends Fragment {

    private MainViewModel mainViewModel;



    private RegistrationFragmentBinding binding;

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        binding.setViewmodel(mainViewModel);
        observeLogin();
    }

    private void observeLogin() {
        mainViewModel.userRepository.userMutableLiveData.observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                mainActivity.navigateToScreen(Constants.DASHBOARD_SCREEN);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mainViewModel.userRepository.userMutableLiveData.removeObservers(getViewLifecycleOwner());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }
}