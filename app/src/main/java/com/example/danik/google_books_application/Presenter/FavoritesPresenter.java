package com.example.danik.google_books_application.Presenter;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.FavoriteBooksContract;

import java.util.List;

public class FavoritesPresenter extends BasePresenter<FavoriteBooksContract.View>
        implements FavoriteBooksContract.Presenter {

    FavoriteBooksContract.Model mModel;

    public FavoritesPresenter(ApplicationEx applicationEx) {
        super(applicationEx);
        mModel = applicationEx.getFavoritesModel();
    }

    @Override
    public void loadData() {
        List<Item> books = mModel.getFavoriteBooks();
        mView.displayFavoritesBooks(books);
    }

    @Override
    public void bookSelected(Item book) {
        application.setCurrentBook(book);
        application.getFragmentNavigation().showBookDetailsFragment();
    }

    @Override
    public void attachView(FavoriteBooksContract.View view) {
        super.attachView( view );
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onResume() {
        loadData();
    }
}
