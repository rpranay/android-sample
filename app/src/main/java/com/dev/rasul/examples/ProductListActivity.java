package com.dev.rasul.examples;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductListActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    MyRecyclerViewAdapter adapter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    Context context = this;
    EditText searchBar;
    BottomNavigationView bnv;
    RecyclerView recyclerView;
    LoadGirdItems l1 = new LoadGirdItems();
    HashMap<String, String> nav_list = new HashMap<>();
    String[] category_title_keys, category_title_values, vendor_title_keys, vendor_title_values;
    public ArrayList<ProductItem> productItemList = new ArrayList<>();
    String type;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //toolbar = findViewById(R.id.my_custom_toolbar);
        //setSupportActionBar(toolbar);


        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        position = intent.getIntExtra("position", 0);
        Log.v("type", type);
        Log.v("position", position + "");

        category_title_keys = getResources().getStringArray(R.array.category_titles);
        category_title_values = getResources().getStringArray(R.array.category_titles_unique);

        vendor_title_keys = getResources().getStringArray(R.array.vendor_titles);
        vendor_title_values = getResources().getStringArray(R.array.vendor_titles_unique);


        l1.execute("http://frugalindian.000webhostapp.com/product_list.php");

        for (int i = 0; i < category_title_keys.length; i++) {
            nav_list.put(category_title_keys[i], category_title_values[i]);
        }

        for (int i = 0; i < vendor_title_keys.length; i++) {
            nav_list.put(vendor_title_keys[i], vendor_title_values[i]);
        }

//
//        Intent i = getIntent();
//        String type = i.getStringExtra("type");
//        int position = i.getIntExtra("position", 0);
//        if (type == "category") {
//            productItemList = filterProductList(type, category_title_values[position]);
//        } else if (type == "vendor") {
//            productItemList = filterProductList(type, vendor_title_values[position]);
//        }
//        adapter = new MyRecyclerViewAdapter(this, productItemList);
//        recyclerView.setAdapter(adapter);


//        searchBar = findViewById(R.id.search_bar);
//        bnv = findViewById(R.id.bottom_navigation);
//        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                item.setChecked(true);
//                switch (item.getItemId()) {
//                    case R.id.action_sort:
//
//                        break;
//                    case R.id.action_filter:
//
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });

//        tabLayout = findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("Product"));
//        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.my_collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("FrugalIndian");

//        searchBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v.getId() == searchBar.getId()) {
//                    searchBar.setCursorVisible(true);
//                }
//            }
//        });

        recyclerView = findViewById(R.id.rvItemGrids);
        mDrawerLayout = findViewById(R.id.dlDrawer);
        mNavigationView = findViewById(R.id.nvDrawer);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "Item clicked " + position, Toast.LENGTH_LONG).show();
        Intent startSinglePageActivity = new Intent(context, SinglePageActivity.class);
        startSinglePageActivity.putExtra("product_id", productItemList.get(position).product_id);
        startSinglePageActivity.putExtra("price", productItemList.get(position).item_price);
        startSinglePageActivity.putExtra("name", productItemList.get(position).item_name);
        startSinglePageActivity.putExtra("rating", productItemList.get(position).item_rating);
        startActivity(startSinglePageActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public ArrayList<ProductItem> filterProductList(String type, String filter) {
        ArrayList<ProductItem> filtered_list = new ArrayList<>();
        for (ProductItem p_item : productItemList) {
            if (type.equals("category")) {
                if (p_item.vendor_category.equals(filter)) {
                    filtered_list.add(p_item);
                }
            } else if (type.equals("vendor")) {
                if (p_item.vendor_name.equals(filter)) {
                    filtered_list.add(p_item);
                }
            }
        }
        return filtered_list;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        String vendor_category = nav_list.get(item.getTitle());
        ArrayList<ProductItem> filtered_list = filterProductList("category", vendor_category);
        adapter = new MyRecyclerViewAdapter(this, filtered_list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


//        switch (item.getItemId()) {
//            case R.id.nav_bedbath:
//                Toast.makeText(this, "nav_bedbath", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_crockery:
//                Toast.makeText(this, "nav_crockery", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_dailyessentials:
//                Toast.makeText(this, "nav_dailyessentials", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_electronics:
//                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_footwear:
//                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_grocery:
//                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_homepersonal:
//                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_luggage:
//                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
//                break;
//            default:
//                break;
//        }
        return true;
    }

    private class LoadGirdItems extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            Log.d("url", strings[0]);
            String jsonStr = sh.makeServiceCall(strings[0]);
            Log.d("jsonstr", jsonStr + "");

            if (jsonStr != null) {

                try {

                    JSONObject complete = new JSONObject(jsonStr);
                    JSONArray tuples;
                    String type_array;
                    // Getting JSON Array node
                    if(type.equals("category")) {
                        type_array = complete.getString("category");
                        tuples = new JSONArray(type_array);
                        Log.v("tuple", tuples+"");
                    }else {
                        type_array= complete.getString("vendor");
                        tuples = new JSONArray(type_array);
                        Log.v("tuple", tuples+"");

                    }

                    // looping through All Contacts
                    for (int i = 0; i < tuples.length(); i++) {
                        JSONObject c = tuples.getJSONObject(i);
                        ProductItem productItem = new ProductItem();
                        productItem.item_name = c.getString("name");
                        productItem.item_price = c.getInt("price");
                        productItem.item_rating = c.getString("rating");
                        productItem.vendor_category = c.getString("vendor_category");
                        productItem.vendor_name = c.getString("vendor_name");
                        productItem.product_id = c.getString("product_id");
                        productItemList.add(productItem);
                        Log.v("ITEM", productItem.item_name + productItem.vendor_category);
                    }

                } catch (final JSONException e) {
                    Log.v("Stacktrace", e+"");
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (type.equals("category")) {
                productItemList = filterProductList(type, category_title_values[position]);
                Log.v("position", productItemList + "");
            } else if (type.equals("vendor")) {
                productItemList = filterProductList(type, vendor_title_values[position]);
                Log.v("position", productItemList + "");
            }
            adapter = new MyRecyclerViewAdapter(context, productItemList);
            recyclerView.setAdapter(adapter);
            adapter.setClickListener(ProductListActivity.this);

        }
    }
}
