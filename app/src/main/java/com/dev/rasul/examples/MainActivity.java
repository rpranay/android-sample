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

public class MainActivity extends AppCompatActivity implements MyHorizontalRecyclerAdapter.HorizontalItemClickListener, MyRecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView, horizontalRecycler;
    private MyRecyclerViewAdapter adapter;
    private MyHorizontalRecyclerAdapter horizontalAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    Context context = this;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

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

        horizontalRecycler = findViewById(R.id.rvHorizontal);
        mDrawerLayout = findViewById(R.id.dlDrawer);
        mNavigationView = findViewById(R.id.nvDrawer);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecycler.setLayoutManager(mLayoutManager);
        horizontalAdapter = new MyHorizontalRecyclerAdapter(this);
        horizontalRecycler.setAdapter(horizontalAdapter);
        horizontalAdapter.setHorizontalClickListener(this);
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
    public void onHorizontalItemClick(View v, int pos) {
        Toast.makeText(context, "Item clicked #" + pos, Toast.LENGTH_LONG).show();

        startActivity(new Intent(MainActivity.this, ProductListActivity.class));
    }

}