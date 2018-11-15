package com.example.danik.google_books_application.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danik.google_books_application.Entities.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.danik.google_books_application.Adapters.OnItemClickListener;
import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.Constants;
import com.example.danik.google_books_application.Activities.MainActivity;
import com.example.danik.google_books_application.R;

public class FavoritesFragment extends Fragment {

    private BookAdapter adapter;
    private List<Item> books;

    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);
        books = null;

        if(getActivity() != null) {
            initRecyclerView();

            getPreferences();
        }

        return view;
    }

    private void getPreferences() {
        SharedPreferences preferences;
        preferences = getActivity().getSharedPreferences(
                Constants.favorites, Context.MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        if(map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                final Item book;
                book = new Gson().
                        fromJson(entry.getValue().toString(), Item.class);
                books.add(book);
            }
            displayItems();
        }
    }

    private void initRecyclerView() {
        books = new ArrayList<>();
        adapter = new BookAdapter();
        adapter.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(Item book) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable( Constants.ARG_TITLE, book);

                    ListItemFragment listItemFragment = new ListItemFragment();
                    listItemFragment.setArguments(bundle);

                    mainActivity.setFragment(listItemFragment);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void displayItems() {
        adapter.setBooks(books);
        adapter.notifyDataSetChanged();
    }
}
