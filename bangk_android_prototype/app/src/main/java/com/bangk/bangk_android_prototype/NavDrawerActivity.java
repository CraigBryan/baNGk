package com.bangk.bangk_android_prototype;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by craigbryan on 19/11/15.
 */
public class NavDrawerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView drawerListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.left_drawer);

        drawerListView.addHeaderView(initializeDrawerHeaderView());
        NavDrawerAdapter adapter = createListAdapter();
        drawerListView.setAdapter(adapter);
    }

    private View initializeDrawerHeaderView() {
        View drawerHeader = getLayoutInflater().inflate(
                R.layout.nav_header, null
        );

        return drawerHeader;
    }

    private NavDrawerAdapter createListAdapter() {
        NavDrawerAdapter adapter = new NavDrawerAdapter(
            this, R.layout.drawer_list_item
        );

        populateNavDrawer(adapter);

        return adapter;
    }

    private void populateNavDrawer(NavDrawerAdapter navList) {
        // Some fake nav items
        navList.add(new NavDrawerItem("Fake Action 1", R.mipmap.questionmark));
        navList.add(new NavDrawerItem("Fake Action 2", R.mipmap.questionmark));
        navList.add(new NavDrawerItem("Fake Action 3", R.mipmap.questionmark));
        // Sign out
        navList.add(new NavDrawerItem("Sign Out", R.mipmap.logout));
    }
}
