package com.sandbox.rader.api;

import com.sandbox.rader.model.ExchangeRateResponse;
import com.sandbox.rader.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(Constants.API_ID + "/kursna_lista")
    Call<ExchangeRateResponse> fetchExchangeRates();
}
