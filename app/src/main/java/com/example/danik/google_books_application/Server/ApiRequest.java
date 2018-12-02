package com.example.danik.google_books_application.Server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {
    public static final String BASE_URL = "https://www.googleapis.com/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static BookAPI getApiService() {
        return getRetrofitInstance().create(BookAPI.class);
    }
}
