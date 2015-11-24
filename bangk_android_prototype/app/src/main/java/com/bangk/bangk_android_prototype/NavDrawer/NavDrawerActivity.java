package com.bangk.bangk_android_prototype.NavDrawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.bangk.bangk_android_prototype.NavDrawer.NavDrawerAdapter;
import com.bangk.bangk_android_prototype.NavDrawer.NavDrawerItem;
import com.bangk.bangk_android_prototype.R;

/**
 * Created by craigbryan on 19/11/15.
 */
public abstract class NavDrawerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView drawerListView;

    protected abstract void setToolbarLabel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.left_drawer);

        drawerListView.addHeaderView(initializeDrawerHeaderView());
        NavDrawerAdapter adapter = createListAdapter();
        drawerListView.setAdapter(adapter);

        setToolbarLabel();
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
        navList.add(new NavDrawerItem(
            "Fake Action 1", "fake1", R.mipmap.questionmark)
        );
        navList.add(new NavDrawerItem(
            "Fake Action 2", "fake2", R.mipmap.questionmark)
        );
        navList.add(new NavDrawerItem(
            "Fake Action 3", "fake3", R.mipmap.questionmark)
        );
        // Sign out
        navList.add(
            new NavDrawerItem("Sign Out", "signout", R.mipmap.logout)
        );
    }
}
