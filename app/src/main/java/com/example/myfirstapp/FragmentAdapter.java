package com.example.myfirstapp;

import android.content.Context;

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

//    public static void updateProducts(List<String> products){
//        this.data = new ArrayList<>(products);
//    }

    public void notifyItemChanged(MyAdapter.ViewHolder holder, int position) {};

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new ProductFragment();
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
