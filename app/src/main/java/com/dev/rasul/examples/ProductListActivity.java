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

public class ProductListActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    MyRecyclerViewAdapter adapter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    Context context = this;
    EditText searchBar;
    BottomNavigationView bnv;
    RecyclerView recyclerView;

    public static ArrayList<String> item_name, item_price, item_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        item_name = new ArrayList<>();
        item_price = new ArrayList<>();
        item_rating = new ArrayList<>();

        toolbar = findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);
        searchBar = findViewById(R.id.search_bar);
        bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.action_sort:

                        break;
                    case R.id.action_filter:

                        break;
                    default:
                        break;
                }
                return true;
            }
        });

//        tabLayout = findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("Product"));
//        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.my_collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("FrugalIndian");

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == searchBar.getId()) {
                    searchBar.setCursorVisible(true);
                }
            }
        });

        recyclerView = findViewById(R.id.rvItemGrids);
        mDrawerLayout = findViewById(R.id.dlDrawer);
        mNavigationView = findViewById(R.id.nvDrawer);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyRecyclerViewAdapter(this);
        adapter.setClickListener(this);
        mNavigationView.setNavigationItemSelectedListener(this);
        LoadGirdItems l1 = new LoadGirdItems();
        l1.execute("http://frugalindian.000webhostapp.com/product.php");

    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "Item clicked " + position, Toast.LENGTH_LONG).show();
        Intent startSinglePageActivity = new Intent(context, SinglePageActivity.class);
        startSinglePageActivity.putExtra("item_id", position);
        startActivity(startSinglePageActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_camera:
                Toast.makeText(this, "camera", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_gallery:
                Toast.makeText(this, "gallery", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_manage:
                Toast.makeText(this, "manage", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_slideshow:
                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
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
                    // Getting JSON Array node
                    JSONArray tuples = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < tuples.length(); i++) {
                        JSONObject c = tuples.getJSONObject(i);
                        item_name.add(c.getString("name"));
                        item_price.add(c.getString("price"));
                        item_rating.add(c.getString("rating"));
                    }
                    Log.d("item name", item_name + "" + item_rating + "" + item_price);

                } catch (final JSONException e) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerView.setAdapter(adapter);

        }
    }
}
