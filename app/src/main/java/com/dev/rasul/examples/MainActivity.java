package com.dev.rasul.examples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    MyRecyclerViewAdapter adapter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    private TabLayout tabLayout;
    Context context = this;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);
        searchBar = findViewById(R.id.search_bar);

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == searchBar.getId()) {
                    searchBar.setCursorVisible(true);
                }
            }
        });

        startActivity(new Intent(this, ProductListActivity.class));
//        RecyclerView recyclerView = findViewById(R.id.rvItemGrids);
//        mDrawerLayout = findViewById(R.id.dlDrawer);
//        mNavigationView = findViewById(R.id.nvDrawer);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        adapter = new MyRecyclerViewAdapter(this);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
//        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onItemClick(View v, int position) {
//        Toast.makeText(this, "Item clicked " + position, Toast.LENGTH_LONG).show();
//        Intent startSinglePageActivity = new Intent(context, SinglePageActivity.class);
//        startSinglePageActivity.putExtra("item_id", position);
//        startActivity(startSinglePageActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        item.setChecked(true);
//        mDrawerLayout.closeDrawers();
//        switch (item.getItemId()) {
//            case R.id.nav_camera:
//                Toast.makeText(this, "camera", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_gallery:
//                Toast.makeText(this, "gallery", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_manage:
//                Toast.makeText(this, "manage", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.nav_slideshow:
//                Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();
//                break;
//            default:
//                break;
//        }
        return true;
    }
}
