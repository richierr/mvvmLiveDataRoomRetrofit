package com.sandbox.rader.ui;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sandbox.rader.api.livaData.CurrenciesLiveData;
import com.sandbox.rader.app.App;
import com.sandbox.rader.model.User;
import com.sandbox.rader.repository.UserRepository;

public class MainViewModel extends ViewModel {

    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public UserRepository userRepository = UserRepository.getInstance();

    public ObservableField<String> userName = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    //LOGIN
    public void login() {




        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                return App.getInstance().getDatabase().userDao().getLoggedInUser(userName.get(), password.get());
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                App.getUserManager().startSession(user);
                userMutableLiveData.postValue(user);
                if(user==null){
                    Toast.makeText(App.getInstance(),"Netacni kredencijali",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();




    }

    //REGISTER

    public void registerUser() {


        new AsyncTask<User, Void, User>() {

            @Override
            protected User doInBackground(User... users) {

                App.getInstance().getDatabase().userDao().insertAll(new User(userName.get(), password.get()));
                return App.getInstance().getDatabase().userDao().getLoggedInUser(userName.get(), password.get());
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                App.getUserManager().startSession(user);
                userMutableLiveData.postValue(user);
            }
        }.execute();
    }

    //DASHBOARD
    public CurrenciesLiveData currenciesLiveData = new CurrenciesLiveData();



    public void logout() {


        App.getUserManager().endSession();
        userMutableLiveData.postValue(null);
    }


}
