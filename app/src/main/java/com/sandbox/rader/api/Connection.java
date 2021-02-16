package com.sandbox.rader.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandbox.rader.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {

    private static ApiService apiService;
    private static OkHttpClient okHttpClient;
    private final Gson gson;


    public Connection(Gson gson) {
        this.gson = gson;
    }


    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                    .addInterceptor(chain -> {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Accept", "application/json")
                                .addHeader("Content-type", "application/json")
                                .addHeader("language", "RS")
                                .addHeader("Authorization", "Bearer " + "")
                                .build();
                        return chain.proceed(request);
                    });
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    public ApiService getApiService() {
        if (apiService == null) {

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.client(getOkHttpClient())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}
