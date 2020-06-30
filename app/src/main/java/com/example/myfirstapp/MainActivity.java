package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.Integer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        boolean next = true;
        //validate first
        EditText keyword = (EditText) findViewById(R.id.keyword_input);
        String keywordText = keyword.getText().toString();

        EditText min_price = (EditText) findViewById(R.id.min_price_input);
        String minPrice = min_price.getText().toString();
        EditText max_price = (EditText) findViewById(R.id.max_price_input);
        String maxPrice = max_price.getText().toString();

        boolean new_cond = ((CheckBox)findViewById(R.id.new_cond)).isChecked();
        boolean used_cond =((CheckBox)findViewById(R.id.used_cond)).isChecked();
        boolean unspecified_cond = ((CheckBox)findViewById(R.id.unspecified_cond)).isChecked();

        Spinner sortBy = ((Spinner)findViewById(R.id.spinner));
        String sort_by = sortBy.getSelectedItem().toString();

        TextView showPriceError = (TextView)findViewById(R.id.price_error);
        TextView showKeywordError = (TextView)findViewById(R.id.keyword_error);

        if(keywordText == null || keywordText.length() == 0) {
            showKeywordError.setVisibility(View.VISIBLE);
            next = false;
        }
        if (!minPrice.equals("") && !maxPrice.equals("") && Integer.parseInt(minPrice) > Integer.parseInt(maxPrice)) {
            showPriceError.setVisibility(View.VISIBLE);
            next = false;
        }
        else {
            showKeywordError.setVisibility(View.INVISIBLE);
            showPriceError.setVisibility(View.INVISIBLE);
            next = true;
        }
        if(next==true) {
            Intent catalogScreenIntent = new Intent(this, CatalogScreen.class);
            catalogScreenIntent.putExtra("keywords", keywordText);
            catalogScreenIntent.putExtra("minPrice", minPrice);
            catalogScreenIntent.putExtra("maxPrice", maxPrice);
            catalogScreenIntent.putExtra("newCond", new_cond);
            catalogScreenIntent.putExtra("usedCond", used_cond);
            catalogScreenIntent.putExtra("unspecifiedCond", unspecified_cond);
            catalogScreenIntent.putExtra("sortBy", sort_by);
            startActivity(catalogScreenIntent);
        }
//        nodesjs url/endpoint?keyword=&sortb
        //res, keyword = req.query.keyword =
    }

    public void clear(View view) {
        TextView showPriceError = (TextView)findViewById(R.id.price_error);
        TextView showKeywordError = (TextView)findViewById(R.id.keyword_error);
        EditText keyword = (EditText) findViewById(R.id.keyword_input);
        keyword.setText("");
        EditText startRange = (EditText) findViewById(R.id.min_price_input);
        startRange.setText("");
        EditText endRange = (EditText) findViewById(R.id.max_price_input);
        endRange.setText("");
        ((CheckBox)findViewById(R.id.new_cond)).setChecked(false);
        ((CheckBox)findViewById(R.id.used_cond)).setChecked(false);
        ((CheckBox)findViewById(R.id.unspecified_cond)).setChecked(false);

        showPriceError.setVisibility(View.INVISIBLE);
        showKeywordError.setVisibility(View.INVISIBLE);
    }

}
