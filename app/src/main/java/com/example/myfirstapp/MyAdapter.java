package com.example.myfirstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<String> products;
    private JSONArray mProducts;

    public MyAdapter(List<String> mProducts){
        this.products = mProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //            holder.product = mProducts.getJSONObject(position);
        holder.product = products.get(position);
        String string1 = "This is a test";
        String string2 = "More tests";
        holder.prodDetailsView.setText(string1);
        holder.priceDetailsView.setText(string2);
    }

    @Override
    public int getItemCount() {
        if(mProducts == null) {
            return 0;
        }
        else {
            return products.size();
        }    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public String product;
        TextView prodDetailsView;
        TextView priceDetailsView;
        //JSONObject product;

        //itemView instance of card layout
        public ViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.card_view);
            prodDetailsView = (TextView)itemView.findViewById(R.id.prodDetailsView);
            priceDetailsView = (TextView)itemView.findViewById(R.id.priceDetailsView);
        }

    }

}