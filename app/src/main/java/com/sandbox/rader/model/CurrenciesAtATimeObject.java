package com.sandbox.rader.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrenciesAtATimeObject {

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("eur")
    @Expose
    private CurrencyModel eur;

    @SerializedName("usd")
    @Expose
    private CurrencyModel usd;

    @SerializedName("chf")
    @Expose
    private CurrencyModel chf;

    @SerializedName("gbp")
    @Expose
    private CurrencyModel gbp;




    public class CurrencyModel {
        @SerializedName("kup")
        @Expose
        private String kup;

        @SerializedName("sre")
        @Expose
        private String sre;

        @SerializedName("pro")
        @Expose
        private String pro;

        public String getKup() {
            return kup;
        }

        public String getSre() {
            return sre;
        }

        public String getPro() {
            return pro;
        }
    }

    public CurrencyModel getEur() {
        return eur;
    }

    public CurrencyModel getUsd() {
        return usd;
    }

    public CurrencyModel getChf() {
        return chf;
    }

    public CurrencyModel getGbp() {
        return gbp;
    }

    public String getDate() {
        return date;
    }
}
