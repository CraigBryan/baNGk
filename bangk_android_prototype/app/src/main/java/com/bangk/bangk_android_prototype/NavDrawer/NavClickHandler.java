package com.bangk.bangk_android_prototype.NavDrawer;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.Toast;

import com.bangk.bangk_android_prototype.R;
import com.bangk.bangk_android_prototype.WelcomeActivity;

/**
 * Created by craigbryan on 23/11/15.
 *
 * Handles click/touch events on the nav drawer menu.
 */
public class NavClickHandler {

    // The context this click handler exists in
    private NavDrawerActivity context;

    // The drawer view that the click handler is acting on
    private DrawerLayout drawer;

    public NavClickHandler(NavDrawerActivity context, DrawerLayout drawer) {
        this.context = context;
        this.drawer = drawer;
    }

    /**
     * Routes the nav drawer action to a fragment or activity.
     * @param action the string representing the nav button pressed
     */
    public void doNavAction(String action) {
        switch(action) {
            case "viewprofile":
                // TODO load profile view
                break;
            case "viewaccounts":
                context.loadFragment(R.layout.view_accounts);
                break;
            case "transfer":
                context.loadFragment(R.layout.bank_transfer);
                break;
            case "signout":
                Intent intent = new Intent(context, WelcomeActivity.class);
                context.finish();
                context.startActivity(intent);
                break;
            case "map":
                context.loadFragment(R.layout.view_map);
                break;
            case "unimplemented":
                Toast.makeText(
                    context, "Feature Unimplemented", Toast.LENGTH_SHORT
                ).show();
                return;
            default:
                Log.e(
                    "baNGk ERROR", "Unknown nav drawer action done: " + action
                );
                return;
        }

        // Animates the drawer closing, after all valid nav actions
        drawer.closeDrawers();
    }
}
