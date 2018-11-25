package com.example.danik.google_books_application.Presenter;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.BookDetailsContract;

public class BookDetailsPresenter extends BasePresenter<BookDetailsContract.View>
        implements BookDetailsContract.Presenter {

    private BookDetailsContract.Model mModel;

    public  BookDetailsPresenter(final ApplicationEx applicationEx) {
        super(applicationEx);
        this.mModel = applicationEx.getDetailsModel();
    }

    @Override
    public void makeFavorite() {
        Item book = getCurrentBook();
        mModel.setFavorite(book);
        mView.displayBook(book, mModel.checkFavorite(book));
    }

    @Override
    public Item getCurrentBook() {
        return application.getCurrentBook();
    }

    @Override
    public void attachView(BookDetailsContract.View view) {
        super.attachView( view );
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onResume() {
        Item book = getCurrentBook();

        mView.displayBook(book, mModel.checkFavorite(book));
    }
}
