package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<String> products;
    private Context context;
    private String count;

    public MyAdapter(Context context, List<String> mProducts, String count){
        this.context = context;
        this.products = mProducts;
        this.count = count;
    }

    public void updateProducts(List<String> products){
        this.products = new ArrayList<>(products);
    }

    public void notifyItemChanged(ViewHolder holder, int position) {};

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
            String title = obj.get("title").toString().replace("[", "").replace("]", "").replace("\"", "");
            String itemID = obj.get("itemId").toString().replace("[", "").replace("]", "");
            Log.d("itemID", "this is it"+itemID);
            //shipping
            JSONArray shippingInfoArray = obj.getJSONArray("shippingInfo");
            JSONObject shippingServiceCostArray = shippingInfoArray.getJSONObject(0);
            JSONArray shippingData = shippingServiceCostArray.getJSONArray("shippingServiceCost");
            JSONObject shippingObj = shippingData.getJSONObject(0);
            String shipping = shippingObj.get("__value__").toString();
            if(shipping.equals("0.0")) shipping = "Free Shipping";
            else shipping = "Ships for $"+shipping;

            //condition
            JSONArray conditionArray = obj.getJSONArray("condition");
            JSONObject conditionDisplayNameArray = conditionArray.getJSONObject(0);
            JSONArray conditionData = conditionDisplayNameArray.getJSONArray("conditionDisplayName");
            String condition = conditionData.get(0).toString();
            String[] condArray = condition.split("", 1);
            condition = condArray[0];

            //String price
            JSONArray costArray = obj.getJSONArray("sellingStatus");
            JSONObject costArray2 = costArray.getJSONObject(0);
            JSONArray costData = costArray2.getJSONArray("currentPrice");
            JSONObject costObj = costData.getJSONObject(0);
            String cost = costObj.get("__value__").toString();
            cost = "$"+cost;

            //image
            String image = obj.get("galleryURL").toString().replace("[", "").replace("]", "").replace("\"", "");
            ProductData data = new ProductData(title, shipping, condition, cost, image, itemID);
            holder.setItem(data);

            String url = obj.get("viewItemURL").toString();
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
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public String product;
        CardView card;
        TextView prodTitle;
        ImageView prodImage;
        TextView prodPrice;
        TextView prodCond;
        TextView prodShips;
        ProductData item;
        String productId;

        //itemView instance of card layout
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    Intent productScreenIntent = new Intent(itemView.getContext(), ProductScreen.class);
                    productScreenIntent.putExtra("productId", productId);
                    itemView.getContext().startActivity(productScreenIntent);
                }
            });
            card = (CardView)itemView.findViewById(R.id.card_view);
            //itemId = (TextView)itemView.findViewById(R.id.item_id);
            prodTitle = (TextView)itemView.findViewById(R.id.prod_title);
            prodImage = (ImageView)itemView.findViewById(R.id.prod_image);
            prodPrice = (TextView)itemView.findViewById(R.id.prod_price);
            prodCond = (TextView)itemView.findViewById(R.id.prod_cond);
            prodShips = (TextView)itemView.findViewById(R.id.prod_ships);
        }

        public void setItem(ProductData data) {
            Log.d("setItem", "set item called"+data.toString());
            prodTitle.setText(data.getProductTitle());
            prodPrice.setText(data.getProductPrice());
            prodCond.setText(data.getProductCondition());
            prodShips.setText(data.getProductShipping());
            Picasso.with(context).load(data.getImage()).into(prodImage);
            productId = data.getProductId();
        }
    }

}