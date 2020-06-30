package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatalogScreen extends AppCompatActivity {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    public MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private JSONArray data;
    private ArrayList<String> products = new ArrayList<>();
    private String count;
    private TextView countText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_screen);
        //final TextView textView = (TextView) findViewById(R.id.text);
        Intent intent = getIntent();
        String keywords = intent.getStringExtra("keywords");
        String minPrice = intent.getStringExtra("minPrice");
        String maxPrice = intent.getStringExtra("maxPrice");
        String newCond = intent.getStringExtra("newCond");
        String usedCond = intent.getStringExtra("usedCond");
        String unspecifiedCond = intent.getStringExtra("unspecifiedCond");
        String sortBy = intent.getStringExtra("sortBy");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(CatalogScreen.this, getProductList(keywords, minPrice, maxPrice, newCond, usedCond, unspecifiedCond, sortBy), count);
        recyclerView.setAdapter(mAdapter);
    }

    /*REQUEST *************************************************************/
    protected ArrayList<String> getProductList(final String keywords, String minPrice, String maxPrice, String newCond, String usedCond, String unspecifiedCond, String sortBy) {
        String url = "https://hw8-ebay-search-back.wl.r.appspot.com/cat?name="+keywords+":"+sortBy+":"+minPrice+":"+maxPrice+":"+newCond+":"+usedCond+":"+unspecifiedCond;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("test", "response!");
                        //String stringResponse = response.toString();
                        //JSONArray key = response.names();
                        try {
                            //data is json array
                            count = response.getJSONArray("searchResult").getJSONObject(0).getString("@count");
                            countText = (TextView) findViewById(R.id.search_results);
                            countText.setText("Showing "+count+" results for "+ keywords);
                            data = response.getJSONArray("searchResult").getJSONObject(0).getJSONArray("item");
                            for (int i = 0; i < data.length(); i++) {
                                //add to string array
                                Log.d("successful subarray!", data.getString(i));
                                products.add(data.getString(i));
                            }
                            mAdapter.updateProducts(products);
                            mAdapter.notifyDataSetChanged();
                            //textView.setText(data.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //textView.setText(stringResponse);
                        //data = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("test", "failure");
                        Toast.makeText(getApplicationContext(), "Nothing found", Toast.LENGTH_SHORT);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);
        return products;
    }
}