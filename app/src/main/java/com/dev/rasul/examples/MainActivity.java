package com.dev.rasul.examples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyHorizontalRecyclerAdapter.HorizontalItemClickListener, MyRecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView categoryHorizontalRecycler, vendorHorizontalRecycler;
    private MyRecyclerViewAdapter adapter;
    private MyHorizontalRecyclerAdapter categoryHorizontalAdapter, vendorHorizontalAdapter;
    private RecyclerView.LayoutManager mLayoutManager1, mLayoutManager2;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar toolbar;
    private Context context = this;
    private TabLayout tabLayout;
    private List<String> vendor_list, category_list;
    static final int TYPE_CATEGORY = 2, TYPE_VENDOR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //toolbar = findViewById(R.id.my_custom_toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("FrugalIndian");

        vendor_list = Arrays.asList(getResources().getStringArray(R.array.vendor_titles));
        category_list = Arrays.asList(getResources().getStringArray(R.array.category_titles));

//        tabLayout = findViewById(R.id.tabs);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Product"));
//        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.my_collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("FrugalIndian");
//        recyclerView = findViewById(R.id.rvItemGrids);


        mDrawerLayout = findViewById(R.id.dlDrawer);
        mNavigationView = findViewById(R.id.nvDrawer);

        vendorHorizontalRecycler = findViewById(R.id.rvHorizontal);
        categoryHorizontalRecycler = findViewById(R.id.rvHorizontal2);

        mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        vendorHorizontalRecycler.setLayoutManager(mLayoutManager1);
        categoryHorizontalRecycler.setLayoutManager(mLayoutManager2);

        vendorHorizontalAdapter = new MyHorizontalRecyclerAdapter(context, vendor_list, TYPE_VENDOR);
        categoryHorizontalAdapter = new MyHorizontalRecyclerAdapter(context, category_list, TYPE_CATEGORY);

        vendorHorizontalRecycler.setAdapter(vendorHorizontalAdapter);
        categoryHorizontalRecycler.setAdapter(categoryHorizontalAdapter);

        vendorHorizontalAdapter.setHorizontalClickListener(this);
        categoryHorizontalAdapter.setHorizontalClickListener(this);
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "Item clicked " + position, Toast.LENGTH_LONG).show();
        Intent startSinglePageActivity = new Intent(context, SinglePageActivity.class);
        startSinglePageActivity.putExtra("item_id", position);
        startActivity(startSinglePageActivity);
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
        return true;
    }

    @Override
    public void onHorizontalItemClick(View v, int pos, int type) {
        Toast.makeText(context, "Item clicked #" + pos + "   " + type, Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, ProductListActivity.class);
        switch (type) {
            case TYPE_VENDOR:
                i.putExtra("type", "vendor");
                i.putExtra("position", pos);
                break;
            case TYPE_CATEGORY:
                i.putExtra("type", "category");
                i.putExtra("position", pos);
                break;
            default:
                break;
        }
        startActivity(i);
    }

}