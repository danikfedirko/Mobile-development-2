package com.example.danik.google_books_application.Activities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.danik.google_books_application.Constants;

import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.FragmentNavigation.FragmentNavigation;
import com.example.danik.google_books_application.Model.DetailsModel;
import com.example.danik.google_books_application.Model.FavoritesModel;
import com.example.danik.google_books_application.Model.ListModel;

public class ApplicationEx extends Application {
    private Item currentBook;
    private FragmentNavigation mFragmentNavigation;
    private SharedPreferences preferences;

    private DetailsModel detailsModel;
    private FavoritesModel favoritesModel;
    private ListModel listModel;

    public FragmentNavigation getFragmentNavigation() {
        return mFragmentNavigation;
    }

    public void setFragmentNavigation(FragmentNavigation fragmentNavigation) {
        mFragmentNavigation = fragmentNavigation;
    }

    public Item getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Item book) {
        currentBook = book;
    }

    public DetailsModel getDetailsModel() {
        return detailsModel;
    }

    public FavoritesModel getFavoritesModel() {
        return favoritesModel;
    }

    public ListModel getListModel() {
        return listModel;
    }

    public SharedPreferences getSharedPreferences() {
        return preferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getApplicationContext()
                .getSharedPreferences(Constants.FAVOURITES, Context.MODE_PRIVATE);

        detailsModel = new DetailsModel(getSharedPreferences());
        favoritesModel = new FavoritesModel(getSharedPreferences());
        listModel = new ListModel();
    }
}
