package com.example.danik.google_books_application.Presenter;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.BooksListContract;

import java.util.List;

public class ListPresenter extends BasePresenter<BooksListContract.View>
        implements BooksListContract.Presenter {
    private BooksListContract.Model mModel;

    public ListPresenter(ApplicationEx applicationEx) {
        super(applicationEx);
        this.mModel = applicationEx.getListModel();
    }

    @Override
    public void loadData() {
        List<Item> books = mModel.getBooksList();
        if(books == null) {
            mView.noData();
        } else {
            mView.displayBooks(books);
        }
    }

    @Override
    public void characterSelected(Item book) {
        application.setCurrentBook(book);
        application.getFragmentNavigation().showBookDetailsFragment();
    }

    @Override
    public void attachView(BooksListContract.View view) {
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
