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
 * The main view container for the logged-in portion of the application.
 * Contains the logic for the nav drawer and the ability to route to various
 * fragments for the actual main screen activity.
 */
public class NavDrawerActivity extends AppCompatActivity {
    public static final String FRAGMENT_INTENT_STRING = "startupFragment";
    public static final String FRAGMENT_TITLE_STRING = "fragmentTitle";

    // The list view that makes up the set of menu elements in the nav drawer
    private ListView drawerListView;

    /**
     * Loads the layout for the nav drawer and initializes the adapter, data,
     * and event handling for the nav drawer. Calls load fragment to load the
     * main content of the screen.
     * @param savedInstanceState
     */
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

    /**
     * Loads a fragment based on a layout id. Displays the title of the feature
     * being displayed and loads the fragment associated with the feature.
     * @param layoutId - ID associated with the layout to be loaded in the
     *                 fragment
     */
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
        // Nav items, titles, adn icon definitions
        navList.add(new NavDrawerItem(
            "Personal Information",
            "viewprofile",
            R.mipmap.ic_face_black_24dp
        ));
        navList.add(new NavDrawerItem(
            "View Accounts",
            "viewaccounts",
            R.mipmap.ic_credit_card_black_24dp
        ));
        navList.add(new NavDrawerItem(
            "Make a Transfer",
            "transfer",
            R.mipmap.ic_compare_arrows_black_24dp
        ));

        // Unimplemented action
        navList.add(new NavDrawerItem(
            "Interac e-Transfer",
            "unimplemented",
            R.mipmap.ic_settings_ethernet_black_24dp
        ));
        // Unimplemented action
        navList.add(new NavDrawerItem(
            "Western Union Transfer",
            "unimplemented",
            R.mipmap.ic_account_balance_black_24dp
        ));

        navList.add(new NavDrawerItem(
            "Find a nearby branch",
            "map",
            R.mipmap.ic_room_black_24dp
        ));
        // Sign out (always last)
        navList.add(
            new NavDrawerItem("Sign Out", "signout", R.mipmap.logout)
        );
    }
}
