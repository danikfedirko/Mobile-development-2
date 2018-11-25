package com.example.danik.google_books_application.Model;

import android.content.SharedPreferences;

import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.FavoriteBooksContract;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritesModel implements FavoriteBooksContract.Model {
    private SharedPreferences mPreferences;

    public FavoritesModel (SharedPreferences preferences) {
        this.mPreferences = preferences;
    }

    public List<Item> getFavoriteBooks() {
        List<Item> booksList = new ArrayList<>();
        Map<String, ?> map = mPreferences.getAll();
        if(map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                final Item book;
                book = new Gson().
                        fromJson(entry.getValue().toString(), Item.class);
                booksList.add(book);
            }
        }
        return booksList;
    }
}
