package com.example.myfirstapp;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private JSONArray mProducts;

    public MyAdapter(JSONArray mProducts){
        this.mProducts = mProducts;
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
        try {
            holder.product = mProducts.getJSONObject(position);
            String string1 = "This is a test";
            String string2 = "More tests";
            holder.prodDetailsView.setText(string1);
            holder.priceDetailsView.setText(string2);
        } catch (JSONException e) {
            Log.e("error in onBind", "secondstring");
        }
    }

    @Override
    public int getItemCount() {
        if(mProducts == null) {
            return 0;
        }
        else {
            return mProducts.length();
        }    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prodDetailsView;
        TextView priceDetailsView;
        JSONObject product;

        //itemView instance of card layout
        public ViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.card_view);
            prodDetailsView = (TextView)itemView.findViewById(R.id.prodDetailsView);
            priceDetailsView = (TextView)itemView.findViewById(R.id.priceDetailsView);
        }

    }

}