package com.example.myfirstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<String> products;
    private Context context;

    public MyAdapter(Context context, List<String> mProducts){
        this.context = context;
        this.products = mProducts;
    }

    public void updateProducts(List<String> products){
        this.products = new ArrayList<>(products);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("test", "creating view holder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("test bind", "creating bind in view holder");
        holder.itemId.setText("test id");
        holder.prodTitle.setText("test title");
    }

    @Override
    public int getItemCount() {
        if(products == null) {
            return 0;
        }
        else {
            return products.size();
        }    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public String product;
        CardView card;
        TextView itemId;
        TextView prodTitle;

        //itemView instance of card layout
        public ViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card_view);
            itemId = (TextView)itemView.findViewById(R.id.item_id);
            prodTitle = (TextView)itemView.findViewById(R.id.prod_title);
        }

    }

}