package com.example.danik.google_books_application.Activities;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.danik.google_books_application.Adapters.BookAdapter;
import com.example.danik.google_books_application.Server.BookAPI;
import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.Entities.Responce;
import com.example.danik.google_books_application.R;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.danik.google_books_application.Server.ApiRequest.request;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.textNoData)
    protected TextView textNoData;
    @BindView(R.id.pullToRefresh)
    protected SwipeRefreshLayout pullToRefresh;

    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bookAdapter = new BookAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadBooks();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBooks();
                pullToRefresh.setRefreshing(false);
            }
        });
    }


    private void loadBooks(){
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
        BookAPI api = request();

        Call<Responce> call = api.getBooks();
        call.enqueue(new Callback<Responce>() {

            @Override
            public void onResponse(Call<Responce> call, Response<Responce> response) {
                List<Item> books = response.body().getItems();

                textNoData.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                bookAdapter.setProducts(books);
                recyclerView.setAdapter(bookAdapter);
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<Responce> call, Throwable t) {
                loading.dismiss();
                recyclerView.setVisibility(View.GONE);
                textNoData.setVisibility(View.VISIBLE);
            }

        });
    }
}
