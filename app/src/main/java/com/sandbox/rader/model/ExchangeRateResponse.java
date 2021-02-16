package com.sandbox.rader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRateResponse {

    @SerializedName("result")
    @Expose
    private CurrenciesAtATimeObject currenciesAtATimeObject;

    public CurrenciesAtATimeObject getCurrenciesAtATimeObject() {
        return currenciesAtATimeObject;
    }
}
