package com.sandbox.rader.repository;


import android.util.Log;




import com.sandbox.rader.api.livaData.CurrenciesLiveData;
import com.sandbox.rader.app.App;
import com.sandbox.rader.model.ExchangeRateResponse;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static UserRepository instance;


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






}
