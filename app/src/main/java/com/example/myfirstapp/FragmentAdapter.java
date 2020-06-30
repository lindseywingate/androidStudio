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

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<String> data;

    public FragmentAdapter(Context context, FragmentManager fm, List<String> data) {
        super(fm);
        mContext = context;
    }

    public void updateProducts(List<String> product){
        this.data = new ArrayList<>(product);
        Log.d("data updated", data.toString());
    }
    public void notifyItemChanged(MyAdapter.ViewHolder holder, int position) {};

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return ProductFragment.newInstance("test", "test2");
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
