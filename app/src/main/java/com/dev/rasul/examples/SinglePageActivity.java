package com.dev.rasul.examples;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SinglePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String[] vendrors;
    MyRecyclerViewAdapter adapter;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Toolbar toolbar;
    private TabLayout tabLayout;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_page_activity);
        toolbar = findViewById(R.id.my_single_page_toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.singleDrawer);
        mNavigationView = findViewById(R.id.singleNavDrawer);
        mNavigationView.setNavigationItemSelectedListener(this);

        tabLayout = findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Product"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));

        vendrors = new String[]{"D-Mart", "More Supermarket", "Reliance Fresh"};

        for (int i = 0; i < 3; i++) {
            LayoutInflater li = getLayoutInflater();
            View v = li.inflate(R.layout.custom_vendor_row, null);
            TextView vendor_name = v.findViewById(R.id.tvVendorNameCustom);
            TextView vendor_price = v.findViewById(R.id.tvVendorPriceCustom);
            vendor_name.setText(vendrors[i]);
            vendor_price.setText("Rs. 21,000");
            ViewGroup insertPoint = findViewById(R.id.llInsertPoint);
            insertPoint.addView(v, i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
//        GridView horizontalGridView = findViewById(R.id.horizontal_gridView);
        //      horizontalGridView.setNumColumns(10);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
