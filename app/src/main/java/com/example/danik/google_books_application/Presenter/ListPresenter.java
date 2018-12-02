package com.example.danik.google_books_application.Presenter;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.Entities.Responce;
import com.example.danik.google_books_application.MVPInterfaces.BooksListContract;
import com.example.danik.google_books_application.Server.ApiRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter extends BasePresenter<BooksListContract.View>
        implements BooksListContract.Presenter {
    private BooksListContract.Model mModel;

    public ListPresenter(ApplicationEx applicationEx) {
        super(applicationEx);
    }

    @Override
    public void loadData() {
        ApiRequest.getApiService().getData().clone().enqueue(new Callback<Responce>() {
            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                if (response.body() == null) {
                    mView.noData();
                } else {
                    mView.displayBooks(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                mView.noData();
            }
        });
    }

    @Override
    public void bookSelected(Item book) {
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
