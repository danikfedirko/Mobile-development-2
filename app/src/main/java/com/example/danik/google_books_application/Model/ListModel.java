package com.example.danik.google_books_application.Model;

import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.BooksListContract;

import java.util.List;

import com.example.danik.google_books_application.Entities.Responce;
import com.example.danik.google_books_application.Server.ApiRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListModel implements BooksListContract.Model {
    private List<Item> booksList;

    @Override
    public List<Item> getBooksList() {
        ApiRequest.getApiService().getData().clone().enqueue( new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                if (response.body() != null) {
                    booksList = response.body().getItems();
                } else {
                    booksList = null;
                }
             }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                booksList = null;
            }
        });
        return booksList;
    }
}
