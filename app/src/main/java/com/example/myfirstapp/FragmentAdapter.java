package com.example.myfirstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<String> data;
    private String title;
    private String cost;
    private String brand;
    private String shipping;
    private String condition;
    ProductData productData;

    public FragmentAdapter(Context context, FragmentManager fm, List<String> data) {
        super(fm);
        mContext = context;
    }

    public void updateProducts(List<String> product) throws JSONException {
        data = new ArrayList<>(product);
        String stuff = product.get(0);
        JSONObject obj = new JSONObject(stuff);
        title = obj.getString("title").toString().replace("]", "").replace("\"", "");

        JSONArray shippingInfoArray = obj.getJSONArray("shippingInfo");
        JSONObject shippingServiceCostArray = shippingInfoArray.getJSONObject(0);
        JSONArray shippingData = shippingServiceCostArray.getJSONArray("shippingServiceCost");
        JSONObject shippingObj = shippingData.getJSONObject(0);
        String shippingString = shippingObj.get("__value__").toString();
        if(shippingString.equals("0.0")) shipping = "Free Shipping";
        else shipping = "Ships for $"+shippingString;

        //condition
        JSONArray conditionArray = obj.getJSONArray("condition");
        JSONObject conditionDisplayNameArray = conditionArray.getJSONObject(0);
        JSONArray conditionData = conditionDisplayNameArray.getJSONArray("conditionDisplayName");
        String conditionString = conditionData.get(0).toString();
        String[] condArray = conditionString.split("", 1);
        condition = condArray[0];

        //String price
        JSONArray costArray = obj.getJSONArray("sellingStatus");
        JSONObject costArray2 = costArray.getJSONObject(0);
        JSONArray costData = costArray2.getJSONArray("currentPrice");
        JSONObject costObj = costData.getJSONObject(0);
        String costString = costObj.get("__value__").toString();
        cost = "$"+costString;

        //image
        String image = obj.get("galleryURL").toString().replace("[", "").replace("]", "").replace("\"", "");
        productData = new ProductData(title, shipping, condition, cost, image, "10");
        Log.d("title", title + cost + condition + shipping);
        //JSONArray title = obj.getJSONArray("title");
    }
    public void notifyItemChanged(MyAdapter.ViewHolder holder, int position) {};

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return ProductFragment.newInstance("This is the Title", "Cost");
            case 1:
                return new SellerInfoFragment();
            case 2:
                return new ShippingFragment();
            default:
                return null;
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_product);
            case 1:
                return mContext.getString(R.string.category_seller_info);
            case 2:
                return mContext.getString(R.string.category_shipping);
            default:
                return null;
        }
    }
}
