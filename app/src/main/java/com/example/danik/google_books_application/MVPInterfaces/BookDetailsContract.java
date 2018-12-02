package com.example.danik.google_books_application.MVPInterfaces;

import com.example.danik.google_books_application.Entities.Item;

public interface BookDetailsContract {
    interface View {
        void displayBook(final Item book, final boolean isFavorite);
    }

    interface Presenter {
        void makeFavorite();
        Item getCurrentBook();
    }

    interface Model {
        void setFavorite(Item book);
        boolean checkFavorite(Item book);
    }
}
