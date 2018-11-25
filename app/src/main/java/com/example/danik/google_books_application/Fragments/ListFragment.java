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
import com.example.danik.google_books_application.Adapters.OnItemClickListener;
import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.Constants;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.Activities.MainActivity;
import com.example.danik.google_books_application.Entities.Responce;
import com.example.danik.google_books_application.R;
import com.example.danik.google_books_application.Server.BookAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.danik.google_books_application.Server.ApiRequest.request;

public class ListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.no_data)
    protected TextView textNoData;
    @BindView(R.id.pullToRefresh)
    protected SwipeRefreshLayout pullToRefresh;

    private BookAdapter bookAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            initRecyclerView();

            textNoData.setVisibility(View.INVISIBLE);
            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadBooks();
                    pullToRefresh.setRefreshing(false);
                }
            });
        }
        loadBooks();

        return view;
    }

    private void loadBooks(){
        BookAPI api = request();

        Call<Responce> call = api.getBooks();
        call.enqueue(new Callback<Responce>() {

            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                List<Item> books = response.body().getItems();

                textNoData.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                bookAdapter.setBooks(books);
                recyclerView.setAdapter(bookAdapter);
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                textNoData.setVisibility(View.VISIBLE);
            }

        });
    }

    private void initRecyclerView() {
        bookAdapter = new BookAdapter();
        recyclerView.setHasFixedSize(true);
        bookAdapter.setOnItemClickListener( new OnItemClickListener() {
            public void onItemClick(Item book) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable( Constants.ARG_TITLE, book);

                    ListItemFragment listItemFragment = new ListItemFragment();
                    listItemFragment.setArguments(bundle);

                    mainActivity.setFragment(listItemFragment, true);
                }
            }
        });
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void noData() {
        bookAdapter.setBooks(null);
        bookAdapter.notifyDataSetChanged();
        textNoData.setVisibility(View.VISIBLE);
    }
}
