package com.dev.rasul.examples;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener{

    MyRecyclerViewAdapter adapter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rvItemGrids);
        mDrawerLayout = findViewById(R.id.dlDrawer);
        mNavigationView = findViewById(R.id.nvDrawer);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MyRecyclerViewAdapter(this);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onItemClick(View v, int position) {
        Toast.makeText(this, "Item clicked "+ position, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()){
            case R.id.nav_camera:Toast.makeText(this, "camera", Toast.LENGTH_LONG).show();break;
            case R.id.nav_gallery:Toast.makeText(this, "gallery", Toast.LENGTH_LONG).show();break;
            case R.id.nav_manage:Toast.makeText(this, "manage", Toast.LENGTH_LONG).show();break;
            case R.id.nav_slideshow:Toast.makeText(this, "slideshow", Toast.LENGTH_LONG).show();break;
            default: break;
        }
        return true;
    }
}
