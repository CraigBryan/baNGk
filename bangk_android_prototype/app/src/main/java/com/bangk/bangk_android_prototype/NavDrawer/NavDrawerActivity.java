package com.bangk.bangk_android_prototype.NavDrawer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bangk.bangk_android_prototype.R;
import com.bangk.bangk_android_prototype.ViewAccountsFragment;

/**
 * Created by craigbryan on 19/11/15.
 */
public class NavDrawerActivity extends AppCompatActivity {
    public static final String FRAGMENT_INTENT_STRING = "startupFragment";
    private ListView drawerListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        drawerListView = (ListView) findViewById(R.id.left_drawer);

        drawerListView.addHeaderView(initializeDrawerHeaderView());
        NavDrawerAdapter adapter = createListAdapter();
        drawerListView.setAdapter(adapter);

        int fragmentId = getIntent().getIntExtra(
            FRAGMENT_INTENT_STRING, R.layout.view_accounts
        );
        loadFragment(fragmentId);
    }

    public void loadFragment(int layoutId) {
        Fragment fragment = null;
        String titleString = null;

        Class fragmentClass;

        switch(layoutId) {
            case R.layout.view_accounts:
                fragmentClass = ViewAccountsFragment.class;
                titleString = getString(R.string.view_accounts_title);
                break;
            default:
                Log.e("baNKg Error", "Attempted to load unknown fragment");
                fragmentClass = ViewAccountsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch(IllegalAccessException | InstantiationException e) {
            Log.e("baNKg Error", "Error loading fragment");
            Log.e("baNKg Error", e.toString());
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(
            R.id.fragment_content, fragment
        ).commit();
        setTitle(titleString);
    }

    private void setTitle(String title) {
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setText(title);
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
            "View Accounts", "viewaccounts", R.mipmap.questionmark)
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
