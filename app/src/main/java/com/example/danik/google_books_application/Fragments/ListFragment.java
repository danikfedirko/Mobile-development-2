package com.example.danik.google_books_application.Fragments;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.danik.google_books_application.Activities.ApplicationEx;
import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.Adapters.OnItemClickListener;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.MVPInterfaces.BooksListContract;
import com.example.danik.google_books_application.Presenter.ListPresenter;
import com.example.danik.google_books_application.R;

public class ListFragment extends Fragment implements BooksListContract.View {

    private BookAdapter adapter;

    private ListPresenter mPresenter;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.pullToRefresh)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_data)
    protected TextView noDataTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mPresenter = new ListPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);

        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            initRecyclerView();

            noDataTextView.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.loadData();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        mPresenter.loadData();

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

    public void displayBooks(final List<Item> books) {
        adapter.setBooks(books);
        adapter.notifyDataSetChanged();
        noDataTextView.setVisibility(View.INVISIBLE);
    }

    public void noData() {
        adapter.setBooks(null);
        adapter.notifyDataSetChanged();
        noDataTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}