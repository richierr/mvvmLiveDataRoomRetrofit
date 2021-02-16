package com.sandbox.rader.repository;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.sandbox.rader.api.livaData.CurrenciesLiveData;
import com.sandbox.rader.app.App;
import com.sandbox.rader.model.ExchangeRateResponse;
import com.sandbox.rader.model.User;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository instance;

    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void fetchExchangeRates(CurrenciesLiveData currenciesLiveData) {
        Call<ExchangeRateResponse> call = App.getInstance().getConnection().getApiService().fetchExchangeRates();
        call.enqueue(new Callback<ExchangeRateResponse>() {
            @Override
            public void onResponse(Call<ExchangeRateResponse> call, Response<ExchangeRateResponse> response) {
               currenciesLiveData.createFrom(response.body());
            }

            @Override
            public void onFailure(Call<ExchangeRateResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    public void registerUser(String userName, String password) {

        new AsyncTask<User, Void, User>() {

            @Override
            protected User doInBackground(User... users) {

                App.getInstance().getDatabase().userDao().insertAll(new User(userName, password));
                return App.getInstance().getDatabase().userDao().getLoggedInUser(userName, password);
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                App.getUserManager().startSession(user);
                userMutableLiveData.postValue(user);
            }
        }.execute();
    }

    public void login(String userName, String password) {

        new AsyncTask<Void, Void, User>() {
            @Override
            protected User doInBackground(Void... voids) {
                return App.getInstance().getDatabase().userDao().getLoggedInUser(userName, password);
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

    public void logout() {
        App.getUserManager().endSession();
        userMutableLiveData.postValue(null);
    }
}
