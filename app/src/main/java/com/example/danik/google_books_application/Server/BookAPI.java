package com.example.danik.google_books_application.Server;

import com.example.danik.google_books_application.Entities.Responce;

import retrofit2.Call;
import retrofit2.http.GET;


public interface BookAPI {

    @GET("/books/v1/volumes?q=java&key=AIzaSyAABGV92nf4smvVlVawsbLDiY-SyWSV1ek")
    Call<Responce> getData();

}