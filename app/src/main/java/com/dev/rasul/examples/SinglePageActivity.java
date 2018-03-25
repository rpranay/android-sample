package com.dev.rasul.examples;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SinglePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String[] vendrors;
    MyRecyclerViewAdapter adapter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    private TabLayout tabLayout;
    Context context = this;
    private TextView item_name, item_price;
    LoadPrices l1 = new LoadPrices();
    public ArrayList<ProductItem> productItemList = new ArrayList<>();
    public int best_price=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_page_activity);
        //toolbar = findViewById(R.id.my_single_page_toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("FrugalIndian");

        mDrawerLayout = findViewById(R.id.singleDrawer);
        mNavigationView = findViewById(R.id.singleNavDrawer);
        mNavigationView.setNavigationItemSelectedListener(this);

        Intent result = getIntent();
        String id = result.getStringExtra("product_id");
        String name = result.getStringExtra("name");
        String rating = result.getStringExtra("rating");

        l1.execute("http://frugalindian.000webhostapp.com/product.php?product_id=" + id);


        item_name = findViewById(R.id.tvItemNameInDetails);
        item_price = findViewById(R.id.tvItemPriceInDetails);

        item_name.setText(name);

//        tabLayout = findViewById(R.id.tabs);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Product"));
//        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));

//        GridView horizontalGridView = findViewById(R.id.horizontal_gridView);
        //      horizontalGridView.setNumColumns(10);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();

        Intent productActivityIntent = new Intent(this, ProductListActivity.class).putExtra("type", "category");
        switch (item.getItemId()) {
            case R.id.nav_bedbath:
                productActivityIntent.putExtra("position", 0);
                break;
            case R.id.nav_crockery:
                productActivityIntent.putExtra("position", 1);
                break;
            case R.id.nav_dailyessentials:
                productActivityIntent.putExtra("position", 2);
                break;
            case R.id.nav_electronics:
                productActivityIntent.putExtra("position", 3);
                break;
            case R.id.nav_footwear:
                productActivityIntent.putExtra("position", 4);
                break;
            case R.id.nav_grocery:
                productActivityIntent.putExtra("position", 5);
                break;
            case R.id.nav_homepersonal:
                productActivityIntent.putExtra("position", 6);
                break;
            case R.id.nav_luggage:
                productActivityIntent.putExtra("position", 7);
                break;
            default:
                break;
        }
        startActivity(productActivityIntent);
        return false;
    }

    private class LoadPrices extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            Log.d("url", strings[0]);
            String jsonStr = sh.makeServiceCall(strings[0]);
            Log.d("jsonstr", jsonStr + "");

            if (jsonStr != null) {

                try {

                    JSONArray tuples = new JSONArray(jsonStr);
                    Log.v("tuple", tuples + "");



                    // looping through All Contacts
                    for (int i = 0; i < tuples.length(); i++) {
                        JSONObject c = tuples.getJSONObject(i);
                        ProductItem productItem = new ProductItem();
                        productItem.item_price = c.getInt("price");
                        productItem.vendor_name = c.getString("vendor_name");
                        productItemList.add(productItem);
                        Log.v("ITEM", productItem.item_name + productItem.vendor_category);
                    }

                } catch (final JSONException e) {
                    Log.v("Stacktrace", e + "");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            int i = 0;
            best_price=productItemList.get(0).item_price;
            for (ProductItem productItem : productItemList) {
                LayoutInflater li = getLayoutInflater();
                View v = li.inflate(R.layout.custom_vendor_row, null);
                TextView vendor_name = v.findViewById(R.id.tvVendorNameCustom);
                TextView vendor_price = v.findViewById(R.id.tvVendorPriceCustom);
                vendor_name.setText(productItem.vendor_name);
                vendor_price.setText("Rs. "+productItem.item_price+"");
                ViewGroup insertPoint = findViewById(R.id.llInsertPoint);
                insertPoint.addView(v, i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                i++;
                if(best_price>productItem.item_price) best_price = productItem.item_price;
            }

            item_price.setText("Rs. "+best_price+"");
        }
    }
}
