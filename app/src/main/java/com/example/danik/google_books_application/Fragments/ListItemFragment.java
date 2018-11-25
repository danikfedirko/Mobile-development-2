package com.example.danik.google_books_application.Fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.BookDetailsContract;
import com.example.danik.google_books_application.Presenter.BookDetailsPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.danik.google_books_application.R;

public class ListItemFragment extends Fragment implements BookDetailsContract.View {

    private BookAdapter adapter;
    private boolean isImageFitToScreen;

    private BookDetailsPresenter mPresenter;

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

        if (getActivity() != null) {
            ButterKnife.bind(this, view);

            initRecyclerView();
        }

        mPresenter = new BookDetailsPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @OnClick(R.id.book_image)
    void fullScreenImage() {
        if(isImageFitToScreen) {
            isImageFitToScreen=false;
            bookImage.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            bookImage.setAdjustViewBounds(true);
        }else{
            isImageFitToScreen=true;
            bookImage.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            bookImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @OnClick(R.id.favorite)
    public void onFavoritesClicked() {
        mPresenter.makeFavorite();
    }

    @Override
    public void displayBook(final Item book, final boolean isFavorite) {
        bookTitle.setText(book.getVolumeInfo().getTitle());
        bookDescription.setText(book.getVolumeInfo().getDescription());
        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(bookImage);
    }

    private void initRecyclerView() {
        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}