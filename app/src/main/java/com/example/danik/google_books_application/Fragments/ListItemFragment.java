package com.example.danik.google_books_application.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.Entities.Item;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.danik.google_books_application.Constants;
import com.example.danik.google_books_application.R;

public class ListItemFragment extends Fragment {

    private BookAdapter adapter;
    private boolean isImageFitToScreen;
    private SharedPreferences preferences;

    private Item book;

    @BindView(R.id.book_title)
    protected TextView bookTitle;
    @BindView(R.id.book_image)
    protected ImageView bookImage;
    @BindView(R.id.book_description)
    protected TextView bookDescription;
    @BindView(R.id.detail_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.favorite)
    protected ImageView favorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_item, container, false);
        ButterKnife.bind(this, view);
        book = (Item) getArguments().getSerializable(Constants.ARG_TITLE);
        displayBook();
        preferences = getActivity().getSharedPreferences(Constants.FAVOURITES, Context.MODE_PRIVATE);
        initRecyclerView();
        checkFavorite();

        return view;
    }

    @OnClick(R.id.book_image)
    void fullScreenImage() {
        if(isImageFitToScreen) {
            isImageFitToScreen=false;
            bookImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            bookImage.setAdjustViewBounds(true);
        }else{
            isImageFitToScreen=true;
            bookImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT));
            bookImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @OnClick(R.id.favorite)
    void setFavorite() {
        SharedPreferences.Editor prefEditor = preferences.edit();
        if(checkFavorite()) {
            prefEditor.remove(book.getVolumeInfo().getTitle());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(book);
            prefEditor.putString(book.getVolumeInfo().getTitle(), json);
            prefEditor.apply();
        }
        checkFavorite();
    }

    boolean checkFavorite() {
        if(preferences.contains(book.getVolumeInfo().getTitle())) {
            favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
            return true;
        } else {
            favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            return false;
        }
    }

    void displayBook() {
        bookTitle.setText(book.getVolumeInfo().getTitle());
        bookDescription.setText(book.getVolumeInfo().getDescription());
        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(bookImage);
    }


    private void initRecyclerView() {
        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
