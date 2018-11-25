package com.example.danik.google_books_application.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Item> books;
    private OnItemClickListener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setBooks(List<Item> books) {
        this.books = books;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Item book = books.get(position);

        viewHolder.textViewTitle.setText(book.getVolumeInfo().getTitle());
        String price;
        if (book.getSaleInfo().getSaleability().equals("FOR_SALE")){
            price = book.getSaleInfo().getListPrice().getAmount() + book.getSaleInfo().getListPrice().getCurrencyCode();
        }
        else{
            price = "not for sale";
        }
        viewHolder.textViewPrice.setText(price);

        Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail()).fit().into(viewHolder.imageView);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(book);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (books != null){
            return books.size();
        }
        else return 0;
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
