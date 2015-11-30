package com.bangk.bangk_android_prototype.NavDrawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bangk.bangk_android_prototype.AccountDetailFragment;
import com.bangk.bangk_android_prototype.MapFragment;
import com.bangk.bangk_android_prototype.R;
import com.bangk.bangk_android_prototype.TransferFragment;
import com.bangk.bangk_android_prototype.ViewAccountsFragment;

/**
 * Created by craigbryan on 19/11/15.
 */
public class NavDrawerActivity extends AppCompatActivity {
    public static final String FRAGMENT_INTENT_STRING = "startupFragment";
    public static final String FRAGMENT_TITLE_STRING = "fragmentTitle";

    private ListView drawerListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);

        drawerListView = (ListView) findViewById(R.id.left_drawer);

        drawerListView.addHeaderView(initializeDrawerHeaderView());
        NavDrawerAdapter adapter = createListAdapter(
            (DrawerLayout) findViewById(R.id.drawer_layout)
        );
        drawerListView.setAdapter(adapter);

        int fragmentId = getIntent().getIntExtra(
            FRAGMENT_INTENT_STRING, R.layout.view_accounts
        );
        loadFragment(fragmentId);
    }

    public void loadFragment(int layoutId) {
        Fragment fragment = null;
        Class fragmentClass;

        String titleString = getIntent().getStringExtra(FRAGMENT_TITLE_STRING);
        Bundle fragmentArgs = new Bundle();

        switch(layoutId) {
            case R.layout.view_accounts:
                if (titleString == null) {
                    titleString = "Your Accounts";
                }
                fragmentClass = ViewAccountsFragment.class;
                break;
            case R.layout.view_account_detail:
                fragmentClass = AccountDetailFragment.class;
                fragmentArgs.putString(
                    AccountDetailFragment.ACCOUNT_NUMBER_KEY,
                    "#123-5423-45235232"
                );
                fragmentArgs.putString(
                    AccountDetailFragment.ACCOUNT_TYPE_KEY,
                    "Chequing Account"
                );
                fragmentArgs.putFloat(
                    AccountDetailFragment.ACCOUNT_BALANCE_KEY, 450.23f
                );
                break;
            case R.layout.bank_transfer:
                titleString = "Make a Transfer";
                fragmentClass = TransferFragment.class;
                break;
            case R.layout.view_map:
                titleString = "Nearby Branches and ATMs";
                fragmentClass = MapFragment.class;
                break;
            default:
                Log.e("baNKg Error", "Attempted to load unknown fragment");
                fragmentClass = ViewAccountsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(fragmentArgs);
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

    private NavDrawerAdapter createListAdapter(DrawerLayout drawer) {
        NavDrawerAdapter adapter = new NavDrawerAdapter(
            this, R.layout.drawer_list_item, drawer
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
            "Make a Transfer", "transfer", R.mipmap.questionmark)
        );
        navList.add(new NavDrawerItem(
            "Find a nearby branch", "map", R.mipmap.questionmark)
        );
        // Sign out
        navList.add(
            new NavDrawerItem("Sign Out", "signout", R.mipmap.logout)
        );
    }
}
