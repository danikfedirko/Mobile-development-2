package com.example.danik.google_books_application.MVPInterfaces;

import com.example.danik.google_books_application.Entities.Item;

import java.util.List;

public interface BooksListContract {
    interface View {
        void noData();
        void displayBooks(final List<Item> books);
    }

    interface Presenter {
        void loadData();
        void bookSelected(Item book);
    }

    interface Model {
        List<Item> getBooksList();
    }
}
