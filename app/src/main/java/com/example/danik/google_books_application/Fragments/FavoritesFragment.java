package com.example.danik.google_books_application.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.FavoriteBooksContract;
import com.example.danik.google_books_application.Presenter.FavoritesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.danik.google_books_application.Adapters.OnItemClickListener;
import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.R;

public class FavoritesFragment extends Fragment implements FavoriteBooksContract.View {

    private BookAdapter adapter;
    private FavoritesPresenter mPresenter;

    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);

        if(getActivity() != null) {
            initRecyclerView();
        }

        mPresenter = new FavoritesPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void initRecyclerView() {
        adapter = new BookAdapter();
        adapter.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(Item book) {
                mPresenter.bookSelected(book);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void displayFavoritesBooks(final List<Item> books) {
        adapter.setBooks(books);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

