package com.example.danik.google_books_application.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.danik.google_books_application.Entities.Item;
import com.example.danik.google_books_application.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Item> books;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    public void setProducts(List<Item> books) {
        this.books = books;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item book = books.get(position);

        holder.textViewTitle.setText(book.getVolumeInfo().getTitle());
        String price;
        if (book.getSaleInfo().getSaleability().equals("FOR_SALE")){
            price = book.getSaleInfo().getListPrice().getAmount() + book.getSaleInfo().getListPrice().getCurrencyCode();
        }
        else{
            price = "not for sale";
        }
        holder.textViewPrice.setText(price);

        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail()).fit().into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.listview_title)
        TextView textViewTitle;
        @BindView(R.id.listview_price)
        TextView textViewPrice;
        @BindView(R.id.listview_image)
        ImageView imageView;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
