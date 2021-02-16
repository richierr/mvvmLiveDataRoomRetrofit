package com.sandbox.rader.api.livaData;

import androidx.databinding.ObservableField;

import com.sandbox.rader.model.ExchangeRateResponse;

public class CurrenciesLiveData {
    public ObservableField<String> date = new ObservableField<>("");
    public ObservableField<String> eurValue = new ObservableField<>("");
    public ObservableField<String> usdValue = new ObservableField<>("");
    public ObservableField<String> chfValue = new ObservableField<>("");
    public ObservableField<String> gbValue = new ObservableField<>("");

    public void createFrom(ExchangeRateResponse body) {
        date.set(body.getCurrenciesAtATimeObject().getDate());
        eurValue.set(body.getCurrenciesAtATimeObject().getEur().getSre());
        usdValue.set(body.getCurrenciesAtATimeObject().getUsd().getSre());
        chfValue.set(body.getCurrenciesAtATimeObject().getChf().getSre());
        gbValue.set(body.getCurrenciesAtATimeObject().getGbp().getSre());
    }
}
