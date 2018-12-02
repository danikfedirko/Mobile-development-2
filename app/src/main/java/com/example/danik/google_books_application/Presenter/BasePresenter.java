package com.example.danik.google_books_application.Presenter;

import com.example.danik.google_books_application.Activities.ApplicationEx;

public abstract class BasePresenter<V>{
    public V mView;
    protected ApplicationEx application;

    public BasePresenter(final ApplicationEx application) {
        this.application = application;
    }

    public void attachView(V view) {
        this.mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public abstract void onResume();
}
