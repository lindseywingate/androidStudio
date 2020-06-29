package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
        String product = products.get(position);
        try {
            JSONObject obj = new JSONObject(product);
            String title = obj.get("title").toString();
            String price = obj.getJSONObject("currentPrice").getJSONObject("convertedCurrentPrice").get("__value__").toString();
            String shipping = obj.getJSONObject("shippingInfo").getJSONObject("shippingServiceCost").get("__value__").toString();
            String condition = obj.getJSONObject("condition").get("conditionDisplayName").toString();
            holder.prodTitle.setText(title);
            //holder.prodPrice.setText(price);
            //holder.prodShips.setText(shipping);
            //holder.prodCond.setText(condition);

            String url = obj.get("viewItemURL").toString();
            Picasso.with(context).load(url).into(holder.prodImage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("fetched prod", product);

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
        //TextView itemId;
        TextView prodTitle;
        ImageView prodImage;
        TextView prodPrice;
        TextView prodCond;
        TextView prodShips;

        //itemView instance of card layout
        public ViewHolder(View itemView) {
            super(itemView);
            card = (CardView)itemView.findViewById(R.id.card_view);
            //itemId = (TextView)itemView.findViewById(R.id.item_id);
            prodTitle = (TextView)itemView.findViewById(R.id.prod_title);
            prodImage = (ImageView)itemView.findViewById(R.id.prod_image);
            prodPrice = (TextView)itemView.findViewById(R.id.prod_price);
            prodCond = (TextView)itemView.findViewById(R.id.prod_cond);
            prodShips = (TextView)itemView.findViewById(R.id.prod_ships);
        }

    }

}