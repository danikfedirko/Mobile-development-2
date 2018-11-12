package com.example.danik.google_books_application.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {
    private static Retrofit retrofit;
    private static BookAPI request;
    public static final String BASE_URL = "https://www.googleapis.com/";

    public static BookAPI request() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BookAPI api = retrofit.create(BookAPI.class);
        request = retrofit.create(BookAPI.class);
        return request;
    }
}
