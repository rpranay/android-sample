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
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    static final int TYPE_CATEGORY = 1, TYPE_VENDOR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);
        vendor_list = Arrays.asList("D-Mart","More Supermarket", "Reliance Fresh");
        category_list = Arrays.asList("Groceries", "Cosmetics", "Dairy Products", "Bed & Bath", "Fruits & Vegetables", "Apparel");

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

        categoryHorizontalRecycler = findViewById(R.id.rvHorizontal);
        vendorHorizontalRecycler = findViewById(R.id.rvHorizontal2);
        mDrawerLayout = findViewById(R.id.dlDrawer);
        mNavigationView = findViewById(R.id.nvDrawer);
        mLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        categoryHorizontalRecycler.setLayoutManager(mLayoutManager1);
        vendorHorizontalRecycler.setLayoutManager(mLayoutManager2);
        categoryHorizontalAdapter = new MyHorizontalRecyclerAdapter(context, vendor_list, TYPE_CATEGORY);
        vendorHorizontalAdapter = new MyHorizontalRecyclerAdapter(context, category_list, TYPE_VENDOR);
        categoryHorizontalRecycler.setAdapter(categoryHorizontalAdapter);
        vendorHorizontalRecycler.setAdapter(vendorHorizontalAdapter);
        categoryHorizontalAdapter.setHorizontalClickListener(this);
        vendorHorizontalAdapter.setHorizontalClickListener(this);
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

    @Override
    public void onHorizontalItemClick(View v, int pos, int type) {
        Toast.makeText(context, "Item clicked #" + pos, Toast.LENGTH_LONG).show();
        switch (type){
            case TYPE_CATEGORY: break;
            case TYPE_VENDOR: break;
            default:break;
        }
        startActivity(new Intent(MainActivity.this, ProductListActivity.class));
    }

}