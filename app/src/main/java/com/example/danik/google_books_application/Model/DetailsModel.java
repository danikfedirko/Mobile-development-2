package com.example.danik.google_books_application.Model;

import android.content.SharedPreferences;

import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.BookDetailsContract;
import com.google.gson.Gson;

public class DetailsModel implements BookDetailsContract.Model {
    private SharedPreferences mPreferences;

    public DetailsModel(SharedPreferences preferences) {
        this.mPreferences = preferences;

    }

    public void setFavorite(Item book) {
        SharedPreferences.Editor prefEditor = mPreferences.edit();
        if(checkFavorite(book)) {
            prefEditor.remove(book.getVolumeInfo().getTitle());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(book);
            prefEditor.putString(book.getVolumeInfo().getTitle(), json);
            prefEditor.apply();
        }
    }

    public boolean checkFavorite(Item book) {
        return mPreferences.contains(book.getVolumeInfo().getTitle());
    }

}
